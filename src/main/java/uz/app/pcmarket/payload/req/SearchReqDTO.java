package uz.app.pcmarket.payload.req;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchReqDTO {
    private Long categoryId;
    private List<Long> itemIds;
    private Double minPrice;
    private Double maxPrice;
}
