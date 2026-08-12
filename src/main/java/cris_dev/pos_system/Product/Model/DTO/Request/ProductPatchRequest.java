package cris_dev.pos_system.Product.Model.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductPatchRequest(
        @Positive
        @NotNull
        Long id,
        @Size(max = 60)
        String name,
        @Size(max = 200)
        String description,
        @Positive
        BigDecimal salePrice,
        @Positive
        Integer stock,
        @Positive
        Integer minimumStock,
        @Size(max = 12)
        String sku,
        String upc,
        String status) {
}