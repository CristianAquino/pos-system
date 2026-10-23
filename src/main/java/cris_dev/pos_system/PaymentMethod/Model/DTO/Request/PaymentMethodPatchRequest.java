package cris_dev.pos_system.PaymentMethod.Model.DTO.Request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PaymentMethodPatchRequest(
        @Size(max = 16, message = "El nombre no puede superar los 16 " + "caracteres")
        @Pattern(regexp = "^[a-zA-Z_]+$", message = "el nombre no puede contener " + "caracteres diferen a letras" + " y guiones bajos")
        String name) {
}
