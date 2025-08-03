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
public class Product extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment image;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<ParamItem> items;
}
