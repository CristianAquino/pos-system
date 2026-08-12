package cris_dev.pos_system.PaymentMethod.Model.Entity;

import cris_dev.pos_system.Sale.Model.Entity.SaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PaymentMethodEntity")
@Table(name = "pos_system_payment_methods")
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "paymentMethod")
    private List<SaleEntity> sales;
    @Column(length = 10, nullable = false)
    private String name = "CONTADO";
    @Column(length = 1, nullable = false)
    private String status = "A";
    @Column(name = "soft_delete")
    private LocalDateTime softDelete;
}