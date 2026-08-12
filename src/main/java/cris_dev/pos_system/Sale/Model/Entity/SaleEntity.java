package cris_dev.pos_system.Sale.Model.Entity;

import cris_dev.pos_system.CashOpening.Model.Entity.CashOpeningEntity;
import cris_dev.pos_system.InventoryMovement.Model.Entity.InventoryMovementEntity;
import cris_dev.pos_system.PaymentMethod.Model.Entity.PaymentMethodEntity;
import cris_dev.pos_system.SaleDetail.Model.Entity.SaleDetailEntity;
import cris_dev.pos_system.User.Model.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SaleEntity")
@Table(name = "pos_system_sales")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cash_opening_id", nullable = false)
    private CashOpeningEntity cashOpening;
    @JoinColumn(name = "payment_method_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentMethodEntity paymentMethod;
    @OneToMany(mappedBy = "sale")
    private List<SaleDetailEntity> saleDetails;
    @OneToMany(mappedBy = "sale")
    private List<InventoryMovementEntity> inventoryMovements;
    @Column(name = "sale_code", length = 200, insertable = false)
    private String saleCode;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;
    @Column(nullable = false)
    private Integer ptax = 0;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal tax = BigDecimal.ZERO;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal total = BigDecimal.ZERO;
    @Column(length = 1, nullable = false)
    private String status = "A";
}