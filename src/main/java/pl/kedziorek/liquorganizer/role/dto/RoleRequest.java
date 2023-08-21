package pl.kedziorek.liquorganizer.role.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class RoleRequest {
    private String id;

    @NotBlank(message = "Name is mandatory!")
    private String name;
}
