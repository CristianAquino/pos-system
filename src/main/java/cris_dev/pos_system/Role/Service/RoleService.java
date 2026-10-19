package cris_dev.pos_system.Role.Service;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleIdResponse;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;

import java.util.List;

public interface RoleService {
    public List<RoleResponse> allRoles(String status);

    public List<RoleResponse> allRolesSoftDelete();

    public RoleIdResponse getRole(String code);

    public RoleResponse createRole(RoleCreateRequest payload);

    public RoleResponse updStatusRole(
            String code);

    public String softDeleteRole(String code);

    public String restoreSoftDeleteRole(String code);
}
