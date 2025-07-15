package uz.app.pcmarket.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.app.pcmarket.entity.abs.AuditEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Parameters extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
    private List<ParamItem> paramItems;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
