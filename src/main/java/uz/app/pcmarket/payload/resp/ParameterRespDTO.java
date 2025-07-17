package uz.app.pcmarket.payload.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParameterRespDTO {
    private Long id;
    private String name;
    private String categoryName;
    private String createAt;
    private List<ItemRespDTO> itemDTOs;
}
