package cris_dev.pos_system.Product.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateRequest(
        @NotBlank(message = "campo obligatorio")
        @Size(max = 60)
        @Pattern(regexp = "^[\\w\\s]+$", message = "no se permiten caracteres diferentes a letras y numeros")
        String name,
        @NotBlank(message = "campo obligatorio")
        @Size(max = 200)
        @Pattern(regexp = "^[\\w\\s]+$", message = "no se permiten caracteres diferentes a letras y numeros")
        String description,
        @Positive
        BigDecimal salePrice,
        @Positive
        Integer stock,
        @Positive
        Integer minimumStock,
        @NotBlank(message = "campo obligatorio")
        @Pattern(regexp = "^[\\w-]+$", message = "no se permiten caracteres diferentes a letras, numeros y guion")
        String sku,
        @NotBlank(message = "campo obligatorio")
        @Size(max = 12)
        @Pattern(regexp = "^\\d{12}$", message = "debe ser una secuencia de 12 numeros")
        String upc,
        String status) {
}