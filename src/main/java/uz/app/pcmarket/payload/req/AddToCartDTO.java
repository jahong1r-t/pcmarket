package uz.app.pcmarket.payload.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddToCartDTO {
    private Long productId;
    private Integer quantity;

    public AddToCartDTO() {
    }

    public AddToCartDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}