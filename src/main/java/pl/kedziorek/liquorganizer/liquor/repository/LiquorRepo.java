package pl.kedziorek.liquorganizer.liquor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kedziorek.liquorganizer.liquor.dto.Liquor;

import java.util.Optional;
import java.util.UUID;

public interface LiquorRepo extends JpaRepository<Liquor, UUID> {
    Optional<Liquor> findById(String id);
}
