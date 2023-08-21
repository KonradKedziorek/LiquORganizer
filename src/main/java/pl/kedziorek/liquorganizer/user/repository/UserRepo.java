package pl.kedziorek.liquorganizer.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kedziorek.liquorganizer.user.dto.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
}
