package pl.kedziorek.liquorganizer.user.dto;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import pl.kedziorek.liquorganizer.role.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "Name is mandatory!")
    private String name;

    @NotBlank(message = "Surname is mandatory!")
    private String surname;

    @NotBlank(message = "Username is mandatory!")
    @Column(unique = true)
    private String username;

    @Email(message = "Email address incorrect!")
    @NotBlank(message = "Email is mandatory!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory!")
    @Column(length = 256)
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private Boolean deleted;

    private Boolean enabled;

    private String confirmationToken;

    private String resetPasswordToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(createdBy, user.createdBy) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(modifiedBy, user.modifiedBy) &&
                Objects.equals(modifiedAt, user.modifiedAt) &&
                Objects.equals(deleted, user.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                surname,
                username,
                email,
                password,
                phoneNumber,
                roles,
                createdBy,
                createdAt,
                modifiedBy,
                modifiedAt,
                deleted
        );
    }

    public static User mapRegistrationRequestToUser(RegistrationRequest request) {
        return User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .deleted(false)
                .enabled(false)
                .build();
    }
}
