package uz.app.pcmarket.payload.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryReqDTO {
    private String name;


    public CategoryReqDTO() {
    }

    public CategoryReqDTO(String name) {
        this.name = name;
    }
}
