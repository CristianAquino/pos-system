package cris_dev.pos_system.Role.Model.DTO.Response;

import java.time.OffsetDateTime;

public record RoleIdResponse(
        Long id,
        String code,
        String description,
        String status,
        OffsetDateTime softDelete) {
}
