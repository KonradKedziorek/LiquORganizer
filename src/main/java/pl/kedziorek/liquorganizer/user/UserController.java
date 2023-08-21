package pl.kedziorek.liquorganizer.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kedziorek.liquorganizer.config.security.AuthResponse;
import pl.kedziorek.liquorganizer.config.security.Credentials;
import pl.kedziorek.liquorganizer.config.security.service.AuthService;
import pl.kedziorek.liquorganizer.user.dto.RegistrationRequest;
import pl.kedziorek.liquorganizer.user.dto.User;
import pl.kedziorek.liquorganizer.user.service.UserService;
import pl.kedziorek.liquorganizer.utils.JwtUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static pl.kedziorek.liquorganizer.utils.Cookie.buildCookie;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthService authenticate;
    private final JwtUtils jwtUtils;

    @Value("${domain}")
    private String domain;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials, HttpServletResponse response) throws IllegalAccessException {
        AuthResponse authResponse = authenticate.authenticate(credentials);
        Cookie tokenCookie = buildCookie(7 * 24 * 60 * 60, true, true, "/", domain, authResponse.getToken(), "token");
        Cookie refreshTokenCookie= buildCookie(7 * 24 * 60 * 60, true, true, "/", domain, authResponse.getRefreshToken(), "refreshToken");
        response.addCookie(tokenCookie);
        response.addCookie(refreshTokenCookie);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
        return ResponseEntity.ok(cookie);
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok().body(userService.signUp(request));
    }
}
