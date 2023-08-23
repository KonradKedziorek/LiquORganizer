package pl.kedziorek.liquorganizer.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CommentReplay {
    private UUID id;
    private List<CommentResponse> commentResponseList;
}
