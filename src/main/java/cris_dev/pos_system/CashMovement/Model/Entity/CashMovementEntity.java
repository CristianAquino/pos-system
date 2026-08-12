package cris_dev.pos_system.CashMovement.Model.Entity;

import cris_dev.pos_system.CashOpening.Model.Entity.CashOpeningEntity;
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
@Entity(name = "CashMovementEntity")
@Table(name = "pos_system_cash_movements")
public class CashMovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cash_opening_id", nullable = false)
    private CashOpeningEntity cashOpening;
    @Column(length = 10, nullable = false)
    private String type = "VENTA";
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
}
