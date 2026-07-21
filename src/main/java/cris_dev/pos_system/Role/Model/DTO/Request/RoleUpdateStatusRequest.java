package cris_dev.pos_system.Role.Model.DTO.Request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateStatusRequest {
    @Size(max = 6)
    @Pattern(regexp = "^\\d+$", message = "error en el codigo del rol")
    private String code;
    @Size(max = 1)
    @Pattern(regexp = "^[aiAI]$")
    private String status;
}
