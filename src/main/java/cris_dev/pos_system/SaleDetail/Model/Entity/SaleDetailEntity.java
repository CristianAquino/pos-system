package cris_dev.pos_system.SaleDetail.Model.Entity;

import cris_dev.pos_system.Product.Model.Entity.ProductEntity;
import cris_dev.pos_system.Sale.Model.Entity.SaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SaleDetailEntity")
@Table(name = "pos_system_sale_details")
public class SaleDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleEntity sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Column(nullable = false)
    private Integer quantity = 0;
    @Column(name = "unit_price", precision = 7, scale = 2, nullable = false)
    private BigDecimal unitPrice = BigDecimal.ONE;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
}
