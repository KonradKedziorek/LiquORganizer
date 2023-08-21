package pl.kedziorek.liquorganizer.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kedziorek.liquorganizer.role.dto.Role;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthInfo {
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
