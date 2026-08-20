package cris_dev.pos_system.User.Repository;

import cris_dev.pos_system.User.Model.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("""
                SELECT r.code
                FROM RoleEntity r
                WHERE r.description = :description
            """)
    public Optional<String> getRoleCode(
            @Param("description") String description);

    @Query("""
                SELECT p
                FROM UserEntity p
                JOIN p.roles r
                WHERE r.code = :code
            """)
    public Page<UserEntity> findUsersRoleCode(
            @Param("code") String code,
            Pageable pageable);
}
