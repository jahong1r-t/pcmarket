package uz.app.pcmarket.payload.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRespDTO {
    private String id;
    private String name;
    private String createAt;

}
