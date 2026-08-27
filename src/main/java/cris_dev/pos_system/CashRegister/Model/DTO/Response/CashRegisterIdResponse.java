package cris_dev.pos_system.CashRegister.Model.DTO.Response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CashRegisterIdResponse(
        UUID id,
        String name,
        String status,
        OffsetDateTime softDelete) {
}
