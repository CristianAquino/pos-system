package cris_dev.pos_system.User.Utils;

import cris_dev.pos_system.User.Model.DTO.Response.UserNormalResponse;
import cris_dev.pos_system.User.Model.DTO.Response.UserResponse;
import cris_dev.pos_system.User.Model.Entity.UserEntity;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserTransform {

    public static UserNormalResponse normalUser(UserEntity entity) {
        return new UserNormalResponse(
                entity.getId(),
                entity.getName(),
                entity.getFatherLastName(),
                entity.getMotherLastName(),
                entity.getFullName(),
                entity.getUsername(),
                entity.getStatus());
    }

    public static UserResponse getUser(UserEntity entity) {
        return new UserResponse(
                entity.getId(),
                entity.getName(),
                entity.getFatherLastName(),
                entity.getMotherLastName(),
                entity.getFullName(),
                entity.getUsername(),
                entity.getStatus(),
                UserTransform.getAllRoles(entity));
    }

    public static List<UserResponse> getAll(List<UserEntity> entity) {
        return entity.stream().map((user) -> new UserResponse(
                user.getId(),
                user.getName(),
                user.getFatherLastName(),
                user.getMotherLastName(),
                user.getFullName(),
                user.getUsername(),
                user.getStatus(),
                UserTransform.getAllRoles(user))).toList();
    }

    public static Set<UserResponse.RoleResponse> getAllRoles(UserEntity entity) {
        return entity.getRoles().stream().map((rol) -> new UserResponse.RoleResponse(
                rol.getId(),
                rol.getDescription())).collect(Collectors.toSet());
    }
}
