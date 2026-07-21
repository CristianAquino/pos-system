package cris_dev.pos_system.Role.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity(name = "RoleEntity")
@Table(name = "pos_system_roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 6)
    private String code;
    @Column(length = 10)
    private String description;
    @Column(length = 1)
    private Character status;
}
