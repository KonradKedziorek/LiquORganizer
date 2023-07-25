package pl.kedziorek.liquorganizer.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {
}
