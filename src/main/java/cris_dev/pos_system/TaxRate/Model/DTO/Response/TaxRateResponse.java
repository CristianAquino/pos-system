package cris_dev.pos_system.TaxRate.Model.DTO.Response;

import java.time.OffsetDateTime;

public record TaxRateResponse(
        Long id,
        Integer rate,
        String status,
        OffsetDateTime effectiveFrom,
        OffsetDateTime effectiveTo) {
}
