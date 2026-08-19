package cris_dev.pos_system.Product.Repository;

import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("""
                SELECT p
                FROM ProductEntity p
                WHERE (:sku IS NULL OR p.sku = :sku )
                AND (:upc IS NULL OR p.upc = :upc)
                AND (:sName IS NULL OR p.searchName LIKE :sName)
            """)
    public List<ProductEntity> searchProducts(
            @Param("sku") String sku,
            @Param("upc") String upc,
            @Param("sName") String sName);

    public Optional<ProductEntity> findBySlug(String slug);

    public List<ProductEntity> findBySlugIsNull();

    public Page<ProductEntity> findBySearchNameContaining(
            String searchName,
            Pageable pageable);

    public Boolean existsBySearchName(String searchName);
}
