package pl.kedziorek.liquorganizer.role.dto;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "Name is mandatory!")
    private String name;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(createdBy, role.createdBy) &&
                Objects.equals(createdAt, role.createdAt) &&
                Objects.equals(modifiedBy, role.modifiedBy) &&
                Objects.equals(modifiedAt, role.modifiedAt) &&
                Objects.equals(deleted, role.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdBy, createdAt, modifiedBy, modifiedAt, deleted);
    }

    public static Role mapToRole(RoleRequest roleRequest) {
        return Role.builder()
                .id(roleRequest.getId().equals("") ? UUID.randomUUID() : UUID.fromString(roleRequest.getId()))
                .name(roleRequest.getName())
                .createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
