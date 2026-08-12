package cris_dev.pos_system.CashRegister.Model.Entity;

import cris_dev.pos_system.CashOpening.Model.Entity.CashOpeningEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CashRegisterEntity")
@Table(name = "pos_system_cash_registers")
public class CashRegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany(mappedBy = "cashRegister")
    private List<CashOpeningEntity> cashOpenings;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 1, nullable = false)
    private String status = "A";
    @Column(name = "soft_delete")
    private LocalDateTime softDelete;
}
