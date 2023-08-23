package pl.kedziorek.liquorganizer.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserRolesRequest {
    private Set<String> roles = new HashSet<>();
}
