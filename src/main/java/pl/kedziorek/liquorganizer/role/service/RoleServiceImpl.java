package pl.kedziorek.liquorganizer.role.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.kedziorek.liquorganizer.config.exception.ResourceNotFoundException;
import pl.kedziorek.liquorganizer.role.dto.Role;
import pl.kedziorek.liquorganizer.role.dto.RoleRequest;
import pl.kedziorek.liquorganizer.role.repository.RoleRepo;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Override
    public Role saveOrUpdateRole(RoleRequest roleRequest) {
        if (Objects.equals(roleRequest.getId(), "")) {
            return saveRole(roleRequest);
        }
        return editRole(roleRequest);
    }

    private Role editRole(RoleRequest roleRequest) {
        Role role = roleRepo.findById(UUID.fromString(roleRequest.getId()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Role with id '" + roleRequest.getId() + "' not found in database")
                );

        log.info("Editing role (name = {})", roleRequest.getName());
        return roleRepo.save(changeProperties(roleRequest, role));
    }

    private Role saveRole(RoleRequest roleRequest) {
        log.info("Saving new role (name = {})", roleRequest.getName());
        return roleRepo.save(Role.mapToRole(roleRequest));
    }

    private Role changeProperties(RoleRequest roleRequest, Role role) {
        role.setName(roleRequest.getName());
        role.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        role.setModifiedAt(LocalDateTime.now());
        return role;
    }
}
