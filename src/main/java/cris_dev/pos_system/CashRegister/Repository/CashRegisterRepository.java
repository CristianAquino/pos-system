package cris_dev.pos_system.CashRegister.Repository;

import cris_dev.pos_system.CashRegister.Model.Entity.CashRegisterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegisterEntity, UUID> {
    @Modifying
    @Query("""
                UPDATE CashRegisterEntity c
                SET c.softDelete = CURRENT_TIMESTAMP, c.status = 'I'
                WHERE c.id = :id
            """)
    Integer softDelete(@Param("id") UUID id);

    @Modifying
    @Query("""
                UPDATE CashRegisterEntity c
                SET c.softDelete = null, c.status = 'I'
                WHERE c.id = :id
            """)
    Integer restoreSoftDelete(@Param("id") UUID id);

    Page<CashRegisterEntity> findBySoftDeleteIsNull(Pageable pageable);

    boolean existsByName(String name);

    List<CashRegisterEntity> findByStatusAndSoftDeleteIsNull(String status);
}
