package cris_dev.pos_system.TaxRate.Repository;

import cris_dev.pos_system.TaxRate.Model.Entity.TaxRateEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRateRepository extends JpaRepositoryImplementation<TaxRateEntity, Long> {
    public List<TaxRateEntity> findAllByOrderByEffectiveToDesc();

    public TaxRateEntity findByStatusAndEffectiveToIsNull(String status);
}
