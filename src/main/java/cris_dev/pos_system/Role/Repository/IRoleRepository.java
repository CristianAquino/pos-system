package cris_dev.pos_system.Role.Repository;

import cris_dev.pos_system.Role.Model.DTO.Response.RoleResponse;
import cris_dev.pos_system.Role.Model.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByCode(String code);
    Boolean existsByDescription(String description);
    List<RoleEntity> findByStatus(Character status);
}
