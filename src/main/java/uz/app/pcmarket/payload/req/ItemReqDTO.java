package uz.app.pcmarket.payload.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemReqDTO {
    private String name;
    private Long paramId;
}
