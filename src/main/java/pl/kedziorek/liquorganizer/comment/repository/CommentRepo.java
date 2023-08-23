package pl.kedziorek.liquorganizer.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kedziorek.liquorganizer.comment.dto.Comment;

import java.util.UUID;

public interface CommentRepo extends JpaRepository<Comment, UUID> {
}
