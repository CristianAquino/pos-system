package cris_dev.pos_system.Role.Repository;

import cris_dev.pos_system.Role.Model.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Modifying
    @Query("""
                UPDATE RoleEntity p
                SET p.softDelete = CURRENT_TIMESTAMP, p.status = 'I'
                WHERE p.id = :id
            """)
    Optional<Integer> softDelete(@Param("id") Long id);

    Optional<RoleEntity> findByDescription(String description);

    List<RoleEntity> findBySoftDeleteIsNull();

    List<RoleEntity> findBySoftDeleteIsNotNull();

    List<RoleEntity> findByStatusAndSoftDeleteIsNull(String status);
}
