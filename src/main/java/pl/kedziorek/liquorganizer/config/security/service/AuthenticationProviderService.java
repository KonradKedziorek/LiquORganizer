package pl.kedziorek.liquorganizer.config.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.kedziorek.liquorganizer.role.dto.Role;
import pl.kedziorek.liquorganizer.user.dto.User;
import pl.kedziorek.liquorganizer.user.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {
    private final UserRepo userRepo;
    private static final String USER_NOT_FOUND_MSG = "User not found with username: ";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG + username));
        Set<Role> roles = user.getRoles();
        roles.forEach(role -> updatedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
        return new UsernamePasswordAuthenticationToken(username, password, updatedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
