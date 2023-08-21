package pl.kedziorek.liquorganizer.config.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kedziorek.liquorganizer.user.dto.User;
import pl.kedziorek.liquorganizer.user.dto.UserAuthInfo;
import pl.kedziorek.liquorganizer.user.repository.UserRepo;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    private static final String USER_NOT_FOUND_MSG = "User not found with username: ";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG + username));
        UserAuthInfo userAuthInfo = UserAuthInfo.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
        return UserDetailsImpl.build(userAuthInfo);
    }
}
