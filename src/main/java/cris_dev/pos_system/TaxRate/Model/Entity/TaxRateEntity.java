package cris_dev.pos_system.TaxRate.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TaxRateEntity")
@Table(name = "pos_system_tax_rates")
public class TaxRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer rate = 18;
    @Column(length = 1, nullable = false)
    private String status = "A";
    @Column(name = "effective_to")
    private Timestamp effectiveTo = null;
}
