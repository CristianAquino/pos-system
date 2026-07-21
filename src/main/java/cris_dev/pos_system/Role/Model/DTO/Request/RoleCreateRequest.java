package cris_dev.pos_system.Role.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateRequest {
    @NotBlank(message = "el nombre del rol es obligatorio")
    @Size(max = 10, message = "El nombre no puede superar los 10 caracteres")
    private String description;
    @Size(max = 1, message = "El estado solamente puede ser A o I")
    @Pattern(regexp = "^[aiAI]$", message = "El estado solamente puede ser A o I")
    private String status;
}
