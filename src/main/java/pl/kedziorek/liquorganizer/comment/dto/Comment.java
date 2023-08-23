package pl.kedziorek.liquorganizer.comment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import pl.kedziorek.liquorganizer.liquor.dto.Liquor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @NotBlank(message = "Content is mandatory!")
    private String content;

    @ManyToOne
    @JoinColumn(name = "liquor_id")
    @JsonIgnore
    private Liquor liquor;

    private Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(createdBy, comment.createdBy) &&
                Objects.equals(createdAt, comment.createdAt) &&
                Objects.equals(modifiedBy, comment.modifiedBy) &&
                Objects.equals(modifiedAt, comment.modifiedAt) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(liquor, comment.liquor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy, createdAt, modifiedBy, modifiedAt, content, liquor);
    }

    public static CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .createdBy(comment.getCreatedBy())
                .build();
    }
}
