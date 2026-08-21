package cris_dev.pos_system.User.Model.DTO.Response;

import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String fatherLastName,
        String motherLastName,
        String fullName,
        String username,
        String status,
        Set<RoleResponse> roles) {
    public record RoleResponse(
            Long id,
            String description) {
    }
}
