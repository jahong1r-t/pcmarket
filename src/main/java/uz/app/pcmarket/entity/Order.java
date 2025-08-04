package uz.app.pcmarket.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.app.pcmarket.entity.abs.AuditEntity;
import uz.app.pcmarket.entity.enums.OrderStatus;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
