package cris_dev.pos_system.User.Model.DTO.Response;

import java.util.UUID;

public record UserNormalResponse(
        UUID id,
        String name,
        String fatherLastName,
        String motherLastName,
        String fullName,
        String username,
        String status) {
}
