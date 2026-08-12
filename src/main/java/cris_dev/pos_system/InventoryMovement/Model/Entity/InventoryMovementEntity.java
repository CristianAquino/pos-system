package cris_dev.pos_system.InventoryMovement.Model.Entity;

import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import cris_dev.pos_system.Sale.Model.Entity.SaleEntity;
import cris_dev.pos_system.User.Model.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "InventoryMovementEntity")
@Table(name = "pos_system_inventory_movements")
public class InventoryMovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleEntity sale;
    @Column(length = 10, nullable = false)
    private String type = "SALE";
    @Column(nullable = false)
    private Integer quantity = 0;
    @Column(name = "previous_stock", nullable = false)
    private Integer previousStock = 0;
    @Column(name = "new_stock", nullable = false)
    private Integer newStock = 0;
}
