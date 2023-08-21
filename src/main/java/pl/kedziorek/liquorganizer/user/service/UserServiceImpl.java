package pl.kedziorek.liquorganizer.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kedziorek.liquorganizer.config.exception.ResourceNotFoundException;
import pl.kedziorek.liquorganizer.role.repository.RoleRepo;
import pl.kedziorek.liquorganizer.user.dto.RegistrationRequest;
import pl.kedziorek.liquorganizer.user.dto.User;
import pl.kedziorek.liquorganizer.user.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final String USER_NOT_FOUND_MSG = "User not found in the database";
    private static final String ROLE_NOT_FOUND_MSG = "Role not found in the database";

    @Value("${createdByRegistration}")
    String defaultCreatedBy;

    @Value("${defaultRole}")
    String defaultRole;

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException(USER_NOT_FOUND_MSG));
    }

    @Override
    public User signUp(RegistrationRequest request) {
        User newUser = User.mapRegistrationRequestToUser(request);
        bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setCreatedBy(defaultCreatedBy);
        newUser.setRoles(Set.of(
                roleRepo.findByName(defaultRole).orElseThrow(() -> new ResourceNotFoundException(ROLE_NOT_FOUND_MSG))
        ));
        return userRepo.save(newUser);
    }
}
