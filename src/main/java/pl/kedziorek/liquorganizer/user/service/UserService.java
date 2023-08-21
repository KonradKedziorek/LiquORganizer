package pl.kedziorek.liquorganizer.user.service;

import pl.kedziorek.liquorganizer.user.dto.RegistrationRequest;
import pl.kedziorek.liquorganizer.user.dto.User;

public interface UserService {
    User getUser(String username);
    User signUp(RegistrationRequest request);
}
