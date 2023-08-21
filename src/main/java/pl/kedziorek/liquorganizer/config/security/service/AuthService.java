package pl.kedziorek.liquorganizer.config.security.service;

import pl.kedziorek.liquorganizer.config.security.AuthResponse;
import pl.kedziorek.liquorganizer.config.security.Credentials;

public interface AuthService {
    AuthResponse authenticate(Credentials credentials) throws IllegalAccessException;
}
