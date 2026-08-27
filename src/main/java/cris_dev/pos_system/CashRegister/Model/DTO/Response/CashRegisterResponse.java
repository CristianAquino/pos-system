package cris_dev.pos_system.CashRegister.Model.DTO.Response;

import java.util.UUID;

public record CashRegisterResponse(
        UUID id,
        String name,
        String status) {
}
