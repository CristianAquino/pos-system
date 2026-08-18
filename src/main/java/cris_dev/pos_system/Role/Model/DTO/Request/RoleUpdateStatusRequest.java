package cris_dev.pos_system.Role.Model.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RoleUpdateStatusRequest(
        @NotNull
        @Positive
        Long id,
        @Size(max = 1)
        @Pattern(regexp = "^[aiAI]$")
        String status) {

}
