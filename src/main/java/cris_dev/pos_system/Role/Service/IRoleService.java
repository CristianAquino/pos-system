package cris_dev.pos_system.Role.Service;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Request.RoleUpdateStatusRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;

import java.util.List;

public interface IRoleService {
    public List<RoleResponse> allRoles(String status);

    public String createRole(RoleCreateRequest role);

    public String updStatusRole(RoleUpdateStatusRequest req);
}
