package pl.kedziorek.liquorganizer.user.service;

import pl.kedziorek.liquorganizer.user.dto.ChangeUserRolesRequest;
import pl.kedziorek.liquorganizer.user.dto.RegistrationRequest;
import pl.kedziorek.liquorganizer.user.dto.User;

import java.util.UUID;

public interface UserService {
    User getUser(String username);
    User signUp(RegistrationRequest request);
    User confirmAccount(String token);
    User changeUserRoles(ChangeUserRolesRequest changeUserRolesRequest, UUID id);
}
