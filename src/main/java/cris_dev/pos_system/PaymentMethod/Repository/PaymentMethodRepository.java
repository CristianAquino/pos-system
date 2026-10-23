package cris_dev.pos_system.PaymentMethod.Repository;

import cris_dev.pos_system.PaymentMethod.Model.Entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long> {
    @Modifying
    @Query("""
                UPDATE PaymentMethodEntity p
                SET p.softDelete = CURRENT_TIMESTAMP, p.status = 'I'
                WHERE p.id = :id
            """)
    Integer softDelete(@Param("id") Long id);

    @Modifying
    @Query("""
                UPDATE PaymentMethodEntity p
                SET p.softDelete = null, p.status = 'A'
                WHERE p.id = :id
            """)
    Integer restoreSoftDelete(@Param("id") Long id);

    boolean existsByName(String name);

    boolean existsByIdAndSoftDeleteIsNull(Long id);

    boolean existsByIdAndSoftDeleteIsNotNull(Long id);

    List<PaymentMethodEntity> findBySoftDeleteIsNull();

    List<PaymentMethodEntity> findBySoftDeleteIsNotNull();

    List<PaymentMethodEntity> findByStatusAndSoftDeleteIsNull(String status);
}
