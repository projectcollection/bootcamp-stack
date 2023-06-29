package com.snva.springboot.bootcamp.dto.mapper;

import com.snva.springboot.bootcamp.dto.model.user.RoleDto;
import com.snva.springboot.bootcamp.model.user.Role;
import org.springframework.stereotype.Component;

/**
 * Created by Arpit Khandelwal.
 */
@Component
public class RoleMapper {

    public static RoleDto toRoleDto(Role role) {
        return new RoleDto()
                .setRole(role.getRole())
                .setId(role.getId());
    }

    public static Role toRole(RoleDto role) {
        return new Role()
                .setRole(role.getRole())
                .setId(role.getId());
    }
}
