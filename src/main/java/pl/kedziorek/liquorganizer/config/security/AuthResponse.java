package pl.kedziorek.liquorganizer.config.security;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;

    private String refreshToken;

    @Builder.Default
    private String type = "Bearer";

    private String username;

    private String uuid;

    private List<String> roles;
}
