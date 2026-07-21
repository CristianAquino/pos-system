package cris_dev.pos_system.Role.Service.Impl;

import cris_dev.pos_system.Role.Model.DTO.Request.RoleCreateRequest;
import cris_dev.pos_system.Role.Model.DTO.Request.RoleUpdateStatusRequest;
import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;
import cris_dev.pos_system.Role.Model.Entity.RoleEntity;
import cris_dev.pos_system.Role.Repository.IRoleRepository;
import cris_dev.pos_system.Role.Service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    final IRoleRepository iRoleRepository;

    @Override
    public List<RoleResponse> allRoles(String status) {
        List<RoleResponse> all;
        if (status == null) {
            all = getAll(iRoleRepository.findAll());
        } else {
            all = getAll(iRoleRepository.findByStatus(status.charAt(0)));
        }
        return all;
    }

    @Override
    public String createRole(RoleCreateRequest role) {
        String role_ = role.getDescription()
                .trim()
                .toUpperCase();
        if (!iRoleRepository.existsByDescription(role_)) {
            RoleEntity newRole = new RoleEntity();
            if (role.getStatus() != null) {
                newRole.setStatus(role.getStatus().toUpperCase().charAt(0));
            }
            newRole.setDescription(role_);
            iRoleRepository.save(newRole);
            return "rol " + role.getDescription() + " creado con exito";
        } else {
            return "rol " + role.getDescription() + " ya existe";
        }
    }

    @Transactional
    @Override
    public String updStatusRole(RoleUpdateStatusRequest req) {
        try {
            RoleEntity role = iRoleRepository
                    .findByCode(req.getCode())
                    .orElseThrow(() -> new RuntimeException("error, usuario no encontrado"));
            if (role.getStatus().toString().equals(req.getStatus().toUpperCase())) {
                throw new RuntimeException("Estado repetido, no se realizaron cambios");
            }
            role.setStatus(req.getStatus().toUpperCase().charAt(0));
            iRoleRepository.save(role);
            return "actualizado correctamente";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<RoleResponse> getAll(List<RoleEntity> entity) {
        List<RoleResponse> resp = entity
                .stream()
                .map(role -> new RoleResponse(
                        role.getId(),
                        role.getCode(),
                        role.getDescription(),
                        role.getStatus().toString()
                )).toList();
        return resp;
    }
}
