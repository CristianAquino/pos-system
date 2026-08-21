package cris_dev.pos_system.Auth.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
        @NotBlank(message = "campo obligatorio")
        @Size(max = 80)
        String name,
        @NotBlank(message = "campo obligatorio")
        @Size(max = 60)
        String fatherLastName,
        @NotBlank(message = "campo obligatorio")
        @Size(max = 60)
        String motherLastName,
        @NotBlank(message = "campo obligatorio")
        @Size(min = 8)
        String password,
        String status) {
}
