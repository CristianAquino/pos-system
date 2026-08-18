package cris_dev.pos_system.Role.Model.Entity;

import cris_dev.pos_system.User.Model.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RoleEntity")
@Table(name = "pos_system_roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
    @Column(length = 6, nullable = false)
    private String code;
    @Column(length = 10, nullable = false)
    private String description;
    @Column(length = 1, nullable = false)
    private String status = "A";
    @Column(name = "soft_delete")
    private LocalDateTime softDelete;
}
