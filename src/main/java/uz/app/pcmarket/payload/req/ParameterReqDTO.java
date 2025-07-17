package uz.app.pcmarket.payload.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParameterReqDTO {
    private Long categoryId;
    private String name;
}