package cris_dev.pos_system.TaxRate.Model.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TaxRateCreateRequest(
        @NotNull(message = "el impuesto es obligatorio")
        @Positive(message = "el impuesto debe ser mayor que 0")
        Integer rate) {
}
