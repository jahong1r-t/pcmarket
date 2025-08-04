package uz.app.pcmarket.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.app.pcmarket.entity.abs.AuditEntity;
import uz.app.pcmarket.entity.enums.Role;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;
}
