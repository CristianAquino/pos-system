package cris_dev.pos_system.CashRegister.Model.DTO.Request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CashRegisterPatchRequest(
        @Size(max = 16, message = "El nombre no puede superar los 16 " + "caracteres")
        @Pattern(regexp = "^[a-zA-Z_]+$", message = "el nombre no puede contener " + "caracteres diferen a letras" + " y guiones bajos")
        String name,
        @Pattern(regexp = "^[aiAI]$", message = "el estado solamente puede ser I o A")
        String status) {
}
