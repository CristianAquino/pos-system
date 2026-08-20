package cris_dev.pos_system.User.Service.Impl;

import cris_dev.pos_system.User.Model.DTO.Request.UserCreateRequest;
import cris_dev.pos_system.User.Model.DTO.Request.UserPatchRequest;
import cris_dev.pos_system.User.Model.DTO.Response.UserResponse;
import cris_dev.pos_system.User.Model.Entity.UserEntity;
import cris_dev.pos_system.User.Repository.UserRepository;
import cris_dev.pos_system.User.Service.UserService;
import cris_dev.pos_system.User.Utils.UserTransform;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserTransform userTransform;

    @Override
    public Page<UserResponse> allUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserTransform::getUser);
    }

    @Override
    public Page<UserResponse> getUsersByRole(
            String description,
            Pageable pageable) {

        if (!description.trim().matches("^[a-zA-Z_]+$")) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "usuarios con rol " + description + " no encontrados");
        }

        String tDescrip = description.trim().toUpperCase();
        String code = userRepository.getRoleCode(tDescrip).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "usuarios con rol " + description + " no encontrados"));

        return userRepository.findUsersRoleCode(
                code,
                pageable).map(UserTransform::getUser);
    }

    @Override
    public UserResponse getUser(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "usuario no encontrado"));
        return userTransform.getUser(user);
    }

    @Override
    public String createUser(UserCreateRequest payload) {
        return "";
    }

    @Override
    public UserResponse updateUser(UserPatchRequest payload) {
        return null;
    }
}
