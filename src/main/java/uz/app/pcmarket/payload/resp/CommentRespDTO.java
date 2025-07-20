package uz.app.pcmarket.payload.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRespDTO {
    private Long id;
    private String content;
    private String userName;
    private String createdAt;
}