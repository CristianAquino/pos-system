package cris_dev.pos_system.Role.Controller;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Request.RoleUpdateStatusRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleIdResponse;
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

    @GetMapping("all/soft_delete")
    public ResponseEntity<List<RoleResponse>> allRolesSoftDelete() {
        List<RoleResponse> roles = roleService.allRolesSoftDelete();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @GetMapping("{code}")
    public ResponseEntity<RoleIdResponse> roleId(
            @Valid @Pattern(regexp = "^\\d{6}$") @PathVariable String code) {
        RoleIdResponse role = roleService.getRole(code);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @PostMapping("create")
    public ResponseEntity<RoleResponse> createRole(
            @Valid @RequestBody RoleCreateRequest payload) {
        RoleResponse role = roleService.createRole(payload);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @PutMapping("update/{code}")
    public ResponseEntity<RoleResponse> updatedStatus(
            @Valid @Pattern(regexp = "^\\d{6}$") @PathVariable String code,
            @Valid @RequestBody RoleUpdateStatusRequest req) {
        RoleResponse role = roleService.updStatusRole(
                code,
                req);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(role);
    }

    @PutMapping("soft_delete/{code}")
    public ResponseEntity<String> updatedSoftDelete(
            @Valid @Pattern(regexp = "^\\d{6}$") @PathVariable String code) {
        String msg = roleService.softDeleteRole(code);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PutMapping("restore/{code}")
    public ResponseEntity<String> restoreSoftDelete(
            @Valid @Pattern(regexp = "^\\d{6}$") @PathVariable String code) {
        String msg = roleService.restoreSoftDeleteCashRegister(code);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
