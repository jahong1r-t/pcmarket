package uz.app.pcmarket.payload.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRespDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String imageId;
    private String createAt;
}
