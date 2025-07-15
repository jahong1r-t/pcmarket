package uz.app.pcmarket.payload.req;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class OrderReqDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Long> productIds;

    public OrderReqDTO () {
    }
}
