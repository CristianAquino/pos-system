package cris_dev.pos_system.Role.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RoleUpdateStatusRequest(
        @NotBlank(message = "este campo es obligatorio")
        @Pattern(regexp = "^[aiAI]$", message = "solamente se acepta A o I")
        String status) {
}
