package cris_dev.pos_system.User.Service;

import cris_dev.pos_system.User.Model.DTO.Request.UserCreateRequest;
import cris_dev.pos_system.User.Model.DTO.Request.UserPatchRequest;
import cris_dev.pos_system.User.Model.DTO.Response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    public Page<UserResponse> allUsers(Pageable pageable);

    public Page<UserResponse> getUsersByRole(
            String descrition,
            Pageable pageable);

    public UserResponse getUser(UUID id);

    public String createUser(UserCreateRequest payload);

    public UserResponse updateUser(UserPatchRequest payload);
}
