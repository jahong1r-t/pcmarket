package uz.app.pcmarket.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.app.pcmarket.entity.abs.AuditEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParamItem extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "filter_id")
    private Parameters filter;
}
