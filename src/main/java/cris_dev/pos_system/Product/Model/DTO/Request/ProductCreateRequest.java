package cris_dev.pos_system.Product.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateRequest(
        @NotBlank(message = "campo obligatorio")
        @Size(max = 60)
        String name,
        @NotBlank(message = "campo obligatorio")
        @Size(max = 200)
        String description,
        @Positive
        BigDecimal salePrice,
        @Positive
        Integer stock,
        @Positive
        Integer minimumStock,
        @NotBlank(message = "campo obligatorio")
        @Size(max = 12)
        String sku,
        @NotBlank(message = "campo obligatorio")
        String upc,
        String status
) {
}