package cris_dev.pos_system.Product.Repository;

import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public Optional<ProductEntity> findBySlug(String slug);

    public List<ProductEntity> findBySlugIsNull();

    public Page<ProductEntity> findBySearchNameContaining(
            String searchName,
            Pageable pageable);

    public Boolean existsBySearchName(String searchName);
}
