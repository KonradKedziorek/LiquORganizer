package pl.kedziorek.liquorganizer.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kedziorek.liquorganizer.config.exception.ResourceNotFoundException;
import pl.kedziorek.liquorganizer.user.dto.User;
import pl.kedziorek.liquorganizer.user.repository.UserRepo;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private static final String USER_NOT_FOUND_MSG = "User not found in the database";

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException(USER_NOT_FOUND_MSG));
    }
}
