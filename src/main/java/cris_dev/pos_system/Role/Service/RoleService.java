package cris_dev.pos_system.Role.Service;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Request.RoleUpdateStatusRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;

import java.util.List;

public interface RoleService {
    public List<RoleResponse> allRoles(String status);

    public List<RoleResponse> allRolesSoftDelete();

    public RoleResponse createRole(RoleCreateRequest payload);

    public RoleResponse updStatusRole(RoleUpdateStatusRequest payload);

    public String softDeleteRole(Long id);
}
