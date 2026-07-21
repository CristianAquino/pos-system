package cris_dev.pos_system.Role.Controller;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Request.RoleUpdateStatusRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;
import cris_dev.pos_system.Role.Service.IRoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("role")
public class RoleController {

    private final IRoleService iRoleService;

    @GetMapping("/all")
    public ResponseEntity<List<RoleResponse>> allRoles(
            @RequestParam(required = false) @Pattern(regexp = "^(A|I)$") String status
                                                      ) {
        List<RoleResponse> roles = iRoleService.allRoles(status);
        return ResponseEntity.status(200).body(roles);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRole(
            @RequestBody @Valid RoleCreateRequest req
                                            ) {
        String message = iRoleService.createRole(req);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/update/status")
    public ResponseEntity<String> updatedStatus(
            @RequestBody @Valid RoleUpdateStatusRequest req
                                               ) {
        try {
            String message = iRoleService.updStatusRole(req);
            return ResponseEntity.accepted().body(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
