package pl.kedziorek.liquorganizer.role.service;

import pl.kedziorek.liquorganizer.role.dto.Role;
import pl.kedziorek.liquorganizer.role.dto.RoleRequest;

public interface RoleService {
    Role saveOrUpdateRole(RoleRequest roleRequest);
}
