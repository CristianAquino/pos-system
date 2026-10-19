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
                UPDATE RoleEntity r
                SET r.softDelete = CURRENT_TIMESTAMP, r.status = 'I'
                WHERE r.code = :code
            """)
    Integer softDelete(@Param("code") String code);

    @Modifying
    @Query("""
                UPDATE RoleEntity r
                SET r.softDelete = null, r.status = 'A'
                WHERE r.code = :code
            """)
    Integer restoreSoftDelete(@Param("code") String code);

    Optional<RoleEntity> findByCode(String code);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndSoftDeleteIsNull(String code);

    Boolean existsByCodeAndSoftDeleteIsNotNull(String code);

    Boolean existsByDescription(String description);

    List<RoleEntity> findBySoftDeleteIsNull();

    List<RoleEntity> findBySoftDeleteIsNotNull();

    List<RoleEntity> findByStatusAndSoftDeleteIsNull(String status);
}
