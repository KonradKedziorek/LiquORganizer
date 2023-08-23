package pl.kedziorek.liquorganizer.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {
    private String createdBy;
    private LocalDateTime createdAt;
    private String content;
}
