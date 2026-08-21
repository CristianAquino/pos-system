package cris_dev.pos_system.User.Controller;

import cris_dev.pos_system.User.Model.DTO.Request.UserPatchRequest;
import cris_dev.pos_system.User.Model.DTO.Response.UserNormalResponse;
import cris_dev.pos_system.User.Model.DTO.Response.UserResponse;
import cris_dev.pos_system.User.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public ResponseEntity<Page<UserResponse>> allUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size);
        Page<UserResponse> users = userService.allUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("rol/{description}")
    public ResponseEntity<Page<UserResponse>> userByRole(
            @Valid() @Size(min = 3, max = 16) @PathVariable String description,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size);
        Page<UserResponse> users = userService.getUsersByRole(
                description,
                pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> userById(
            @PathVariable UUID id) {
        UserResponse user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PatchMapping("update")
    public ResponseEntity<UserNormalResponse> patchUser(@Valid @RequestBody UserPatchRequest payload) {
        UserNormalResponse user = userService.updateUser(payload);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }
}
