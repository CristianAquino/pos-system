package cris_dev.pos_system.Role.Utils;

import cris_dev.pos_system.Role.Model.DTO.Response.RoleIdResponse;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;
import cris_dev.pos_system.Role.Model.Entity.RoleEntity;

import java.util.List;

public class RoleTransform {
    public static RoleResponse role(RoleEntity entity) {
        return new RoleResponse(
                entity.getId(),
                entity.getCode(),
                entity.getDescription(),
                entity.getStatus());
    }

    public static RoleIdResponse roleId(RoleEntity entity) {
        return new RoleIdResponse(
                entity.getId(),
                entity.getCode(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getSoftDelete());
    }

    public static List<RoleResponse> roleList(List<RoleEntity> entity) {
        return entity.stream().map(RoleTransform::role).toList();
    }
}
