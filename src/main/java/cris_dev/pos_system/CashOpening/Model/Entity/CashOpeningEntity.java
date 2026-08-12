package cris_dev.pos_system.CashOpening.Model.Entity;


import cris_dev.pos_system.CashMovement.Model.Entity.CashMovementEntity;
import cris_dev.pos_system.CashRegister.Model.Entity.CashRegisterEntity;
import cris_dev.pos_system.Sale.Model.Entity.SaleEntity;
import cris_dev.pos_system.User.Model.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CashOpeningEntity")
@Table(name = "pos_system_cash_openings")
public class CashOpeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cash_register_id", nullable = false)
    private CashRegisterEntity cashRegister;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @OneToMany(mappedBy = "cashOpening")
    private List<SaleEntity> sales;
    @OneToMany(mappedBy = "cashOpening")
    private List<CashMovementEntity> cashMovements;
    @Column(name = "opening_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal openingAmount = BigDecimal.ZERO;
    @Column(name = "cash_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal cashAmount = BigDecimal.ZERO;
    @Column(name = "yape_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal yapeAmount = BigDecimal.ZERO;
    @Column(name = "plin_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal plinAmount = BigDecimal.ZERO;
    @Column(name = "card_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal cardAmount = BigDecimal.ZERO;
    @Column(name = "closing_amount", precision = 10, scale = 2)
    private BigDecimal closingAmount;
    @Column(name = "expect_cash_amount", precision = 10, scale = 2)
    private BigDecimal expectCashAmount;
    @Column(name = "opened_at", insertable = false, updatable = false)
    private LocalDateTime openedAt;
    @Column(name = "closed_at")
    private LocalDateTime closedAt;
    @Column(length = 1, nullable = false)
    private String status = "A";
}
