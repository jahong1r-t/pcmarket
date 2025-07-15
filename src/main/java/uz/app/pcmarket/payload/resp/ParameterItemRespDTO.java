package uz.app.pcmarket.payload.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParameterItemRespDTO {
    private Long id;
    private String name;
}
