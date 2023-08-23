package pl.kedziorek.liquorganizer.comment.service;

import pl.kedziorek.liquorganizer.comment.dto.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    Comment saveComment(Comment comment);
    List<Comment> getAllCommentsOfLiquor(UUID uuid);
}
