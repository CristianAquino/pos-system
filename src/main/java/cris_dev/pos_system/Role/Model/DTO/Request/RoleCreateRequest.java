package cris_dev.pos_system.Role.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleCreateRequest(
        @NotBlank(message = "el nombre del rol es obligatorio")
        @Size(max = 10, message = "El nombre no puede superar los 10 caracteres")
        String description) {
}
