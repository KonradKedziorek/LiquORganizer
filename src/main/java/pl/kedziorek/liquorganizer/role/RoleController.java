package pl.kedziorek.liquorganizer.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kedziorek.liquorganizer.role.dto.RoleRequest;
import pl.kedziorek.liquorganizer.role.service.RoleService;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<?> saveRole(
            @Validated
            @RequestBody
                    RoleRequest roleRequest) {
        return ResponseEntity.ok().body(roleService.saveOrUpdateRole(roleRequest));
    }
}
