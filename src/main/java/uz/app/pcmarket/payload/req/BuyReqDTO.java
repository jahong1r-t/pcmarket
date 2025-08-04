package uz.app.pcmarket.payload.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuyReqDTO {
    private Long productId;

    @NotBlank(message = "Product ID must not be empty")
    private String fullName;

    @NotBlank(message = "Phone number must not be empty")
    private String phoneNumber;
}
