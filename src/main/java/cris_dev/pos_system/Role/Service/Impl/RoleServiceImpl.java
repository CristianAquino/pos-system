package cris_dev.pos_system.Role.Service.Impl;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Request.RoleUpdateStatusRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;
import cris_dev.pos_system.Role.Model.Entity.RoleEntity;
import cris_dev.pos_system.Role.Repository.RoleRepository;
import cris_dev.pos_system.Role.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    @Override
    public List<RoleResponse> allRoles(String status) {
        if (status == null) {
            return getAll(roleRepository.findBySoftDeleteIsNull());
        }
        String nStatus = status.trim().toUpperCase();
        return getAll(roleRepository.findByStatusAndSoftDeleteIsNull(nStatus));
    }

    @Override
    public List<RoleResponse> allRolesSoftDelete() {
        return getAll(roleRepository.findBySoftDeleteIsNotNull());
    }

    @Override
    public RoleResponse createRole(RoleCreateRequest payload) {
        String role_ = payload.description().trim().toUpperCase();

        roleRepository.findByDescription(role_).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.CONFLICT,
                "rol " + payload.description() + " ya existe"));

        RoleEntity newRole = new RoleEntity();

        newRole.setDescription(role_);
        RoleEntity role = roleRepository.save(newRole);
        return new RoleResponse(
                role.getId(),
                role.getCode(),
                role.getDescription(),
                role.getStatus());
    }

    @Transactional
    @Override
    public RoleResponse updStatusRole(RoleUpdateStatusRequest payload) {
        RoleEntity role = roleRepository.findById(payload.id()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "usuario no encontrado"));

        if (role.getStatus().equals(payload.status().toUpperCase())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Estado repetido, no se realizaron cambios");
        }

        role.setStatus(payload.status().toUpperCase());
        RoleEntity role_ = roleRepository.save(role);

        return new RoleResponse(
                role_.getId(),
                role_.getCode(),
                role_.getDescription(),
                role_.getStatus());
    }

    @Transactional
    @Override
    public String softDeleteRole(Long id) {
        RoleEntity role = roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No se pudo encontrar el rol"));

        roleRepository.softDelete(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "No se pudo realizar la eliminacion del rol " + role.getDescription()));

        return "Se elimino el role " + role.getDescription();
    }

    public static List<RoleResponse> getAll(List<RoleEntity> entity) {
        List<RoleResponse> roles = entity.stream().map(role -> new RoleResponse(
                role.getId(),
                role.getCode(),
                role.getDescription(),
                role.getStatus())).toList();
        return roles;
    }
}
