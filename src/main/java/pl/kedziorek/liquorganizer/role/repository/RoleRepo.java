package pl.kedziorek.liquorganizer.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kedziorek.liquorganizer.role.dto.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {
    Optional<Role> findById(UUID id);
}
