package cris_dev.pos_system.Product.Model.Entity;

import cris_dev.pos_system.InventoryMovement.Model.Entity.InventoryMovementEntity;
import cris_dev.pos_system.SaleDetail.Model.Entity.SaleDetailEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ProductEntity")
@Table(name = "pos_system_products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "product")
    private List<SaleDetailEntity> saleDetails;
    @OneToMany(mappedBy = "product")
    private List<InventoryMovementEntity> inventoryMovements;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(name = "sale_price", precision = 7, scale = 2, nullable = false)
    private BigDecimal salePrice = BigDecimal.ONE;
    @Column(nullable = false)
    private Integer stock = 0;
    @Column(name = "minimum_stock", nullable = false)
    private Integer minimumStock = 1;
    @Column(columnDefinition = "TEXT")
    private String slug;
    @Column(name = "search_name", columnDefinition = "TEXT")
    private String searchName;
    @Column(length = 12, nullable = false)
    private String sku;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String upc;
    @Column(length = 1, nullable = false)
    private String status = "A";
}


