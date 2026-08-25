package cris_dev.pos_system.PaymentMethod.Model.DTO.Response;

import java.time.OffsetDateTime;

public record PaymentMethodIdResponse(
        Long id,
        String name,
        String status,
        OffsetDateTime softDelete) {
}
