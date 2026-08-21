package cris_dev.pos_system.User.Model.Entity;

import cris_dev.pos_system.CashOpening.Model.Entity.CashOpeningEntity;
import cris_dev.pos_system.InventoryMovement.Model.Entity.InventoryMovementEntity;
import cris_dev.pos_system.Role.Model.Entity.RoleEntity;
import cris_dev.pos_system.Sale.Model.Entity.SaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "UserEntity")
@Table(name = "pos_system_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pos_system_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;
    @OneToMany(mappedBy = "user")
    private List<SaleEntity> sales;
    @OneToMany(mappedBy = "user")
    private List<CashOpeningEntity> cashOpenings;
    @OneToMany(mappedBy = "user")
    private List<InventoryMovementEntity> inventoryMovements;
    @Column(length = 80, nullable = false)
    private String name;
    @Column(name = "father_last_name", length = 60, nullable = false)
    private String fatherLastName;
    @Column(name = "mother_last_name", length = 60, nullable = false)
    private String motherLastName;
    @Column(name = "full_name", length = 220, nullable = false)
    private String fullName;
    @Column(length = 60, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length = 1, nullable = false)
    private String status = "A";
    @Column(name = "soft_delete")
    private LocalDateTime softDelete;
}
