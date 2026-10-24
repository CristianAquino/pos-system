package cris_dev.pos_system.CashRegister.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CashRegisterCreateRequest(
        @NotBlank(message = "el nombre de la caja registradora es obligatorio")
        @Size(max = 16, message = "El nombre no puede superar los 16 caracteres")
        @Pattern(regexp = "^[a-zA-Z_]+$", message = "la caja registradora no " + "puede contener " + "caracteres diferentes a letras y guiones" + " bajos")
        String name) {
}
