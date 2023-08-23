package pl.kedziorek.liquorganizer.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kedziorek.liquorganizer.comment.dto.Comment;
import pl.kedziorek.liquorganizer.comment.repository.CommentRepo;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private static final String SAVE_COMMENT_MSG = "Saving new comment to the database";

    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        comment.setCreatedAt(LocalDateTime.now());
        log.info(SAVE_COMMENT_MSG);
        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> getAllCommentsOfLiquor(UUID uuid) {
        return null;
    }
}
