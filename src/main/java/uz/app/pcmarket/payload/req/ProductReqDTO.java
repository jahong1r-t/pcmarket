package uz.app.pcmarket.payload.req;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class ProductReqDTO {
    @NotBlank(message = "Product name must not be empty")
    private String name;

    @NotBlank(message = "Description must not be empty")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "Quantity must not be empty")
    @Pattern(regexp = "\\d+", message = "Quantity must be a valid number")
    private String quantity;

    @NotNull(message = "Image file is required")
    private MultipartFile image;

    @NotEmpty(message = "At least one item must be selected")
    private List<@NotNull(message = "Item ID must not be null") Long> itemIds;
}

