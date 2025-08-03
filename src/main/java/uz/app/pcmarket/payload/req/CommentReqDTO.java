package uz.app.pcmarket.payload.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReqDTO {
    @NotBlank(message = "Comment content must not be empty")
    private String content;

   /* public CommentReqDTO(@NotBlank(message = "Comment content must not be empty") String content) {
        this.content = content;
    }*/
}