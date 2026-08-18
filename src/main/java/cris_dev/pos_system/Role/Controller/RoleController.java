package cris_dev.pos_system.Role.Controller;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Request.RoleUpdateStatusRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;
import cris_dev.pos_system.Role.Service.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("all")
    public ResponseEntity<List<RoleResponse>> allRoles(
            @RequestParam(required = false) @Pattern(regexp = "^[aiAI]$") String status) {
        List<RoleResponse> roles = roleService.allRoles(status);
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @GetMapping("soft_delete/all")
    public ResponseEntity<List<RoleResponse>> allRolesSoftDelete() {
        List<RoleResponse> roles = roleService.allRolesSoftDelete();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @PostMapping("create")
    public ResponseEntity<RoleResponse> createRole(
            @RequestBody @Valid RoleCreateRequest payload) {
        RoleResponse role = roleService.createRole(payload);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @PutMapping("update/status")
    public ResponseEntity<RoleResponse> updatedStatus(
            @RequestBody @Valid RoleUpdateStatusRequest req) {
        RoleResponse role = roleService.updStatusRole(req);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(role);
    }

    @PutMapping("soft_delete/{id}")
    public ResponseEntity<String> updatedSoftDelete(@PathVariable Long id) {
        String msg = roleService.softDeleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
