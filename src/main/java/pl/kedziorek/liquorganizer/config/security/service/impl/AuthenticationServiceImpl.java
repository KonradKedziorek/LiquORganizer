package pl.kedziorek.liquorganizer.config.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kedziorek.liquorganizer.config.exception.ResourceNotFoundException;
import pl.kedziorek.liquorganizer.config.security.AuthResponse;
import pl.kedziorek.liquorganizer.config.security.Credentials;
import pl.kedziorek.liquorganizer.config.security.service.AuthService;
import pl.kedziorek.liquorganizer.config.security.service.AuthenticationProviderService;
import pl.kedziorek.liquorganizer.user.dto.User;
import pl.kedziorek.liquorganizer.user.repository.UserRepo;
import pl.kedziorek.liquorganizer.user.service.UserService;
import pl.kedziorek.liquorganizer.utils.JwtUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProviderService authenticationProviderService;
    private final JwtUtils jwtUtils;

    private static final String USER_NOT_FOUND = "User not found in the database!";
    private static final String ILLEGAL_ACCESS = "Access denied!";

    @Override
    public AuthResponse authenticate(Credentials credentials) throws IllegalAccessException {
        User user = userService.getUser(credentials.getUsername());
        String password = credentials.getPassword();
        if(user!=null
                && (passwordEncoder.matches(password, user.getPassword()))
                && Boolean.TRUE.equals(user.getEnabled())){
            Authentication authentication = databaseAuthenticate(credentials);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            String refreshToken = jwtUtils.generateRefreshToken(authentication);
            UUID uuid = userRepo.findByUsername(authentication.getName())
                    .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND)).getId();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            return AuthResponse.builder()
                    .token(jwt)
                    .username(authentication.getName())
                    .refreshToken(refreshToken)
                    .uuid(uuid.toString())
                    .roles(roles)
                    .build();
        }
        throw new IllegalAccessException(ILLEGAL_ACCESS);
    }

    private Authentication databaseAuthenticate(Credentials credentials) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        return authenticationProviderService.authenticate(usernamePasswordAuthenticationToken);
    }
}
