package uz.app.pcmarket.payload.resp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class OrderRespDTO {
    private Long id;
    private String productName;
    private UUID productImage;
    private String productCount;
    private Double totalPrice;

    private LocalDate orderDate;
    private String userName;
    private String userPhone;
    private String status;
}
