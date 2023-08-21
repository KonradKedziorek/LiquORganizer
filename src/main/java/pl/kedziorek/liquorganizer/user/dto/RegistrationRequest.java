package pl.kedziorek.liquorganizer.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class RegistrationRequest {
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
    private String password;

    @Column(unique = true)
    private String phoneNumber;
}
