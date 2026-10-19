package cris_dev.pos_system.Role.Service.Impl;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleIdResponse;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;
import cris_dev.pos_system.Role.Model.Entity.RoleEntity;
import cris_dev.pos_system.Role.Repository.RoleRepository;
import cris_dev.pos_system.Role.Service.RoleService;
import cris_dev.pos_system.Role.Utils.RoleTransform;
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
            return RoleTransform.roleList(roleRepository.findBySoftDeleteIsNull());
        }
        String nStatus = status.trim().toUpperCase();
        return RoleTransform.roleList(roleRepository.findByStatusAndSoftDeleteIsNull(nStatus));
    }

    @Override
    public List<RoleResponse> allRolesSoftDelete() {
        return RoleTransform.roleList(roleRepository.findBySoftDeleteIsNotNull());
    }

    @Override
    public RoleIdResponse getRole(String code) {
        RoleEntity rol = roleRepository.findByCode(code).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "role no encontrado"));

        return RoleTransform.roleId(rol);
    }

    @Transactional
    @Override
    public RoleResponse createRole(RoleCreateRequest payload) {
        String description = payload.description().trim().toUpperCase();

        if (roleRepository.existsByDescription(description)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "rol " + payload.description() + " ya existe");
        }

        RoleEntity nRole = new RoleEntity();
        nRole.setDescription(description);

        RoleEntity role = roleRepository.save(nRole);

        return RoleTransform.role(role);
    }

    @Transactional
    @Override
    public RoleResponse updStatusRole(
            String code) {
        RoleEntity role = roleRepository.findByCode(code).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "role no encontrado"));

        if (role.getStatus().equals("A")) {
            role.setStatus("I");
        } else {
            role.setStatus("A");
        }

        return RoleTransform.role(role);
    }

    @Transactional
    @Override
    public String softDeleteRole(String code) {
        if (!roleRepository.existsByCodeAndSoftDeleteIsNull(code)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar el rol o ya se encuentra eliminado");
        }
        int affected = roleRepository.softDelete(code);

        if (affected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se pudo realizar la eliminacion del rol");
        }

        return "Se realizo la eliminacion del rol";
    }

    @Transactional
    @Override
    public String restoreSoftDeleteRole(String code) {
        if (!roleRepository.existsByCodeAndSoftDeleteIsNotNull(code)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar el rol o ya se encuentra restaurado");
        }
        int affected = roleRepository.restoreSoftDelete(code);

        if (affected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se pudo realizar la restauracion del rol");
        }

        return "Se realizo la restauracion del rol";
    }
}