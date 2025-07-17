package uz.app.pcmarket.payload.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemRespDTO {
    private Long id;
    private String name;
    private String parameterName;
    private String categoryName;
    private String createAt;
}
