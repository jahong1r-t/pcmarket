package uz.app.pcmarket.service;

import uz.app.pcmarket.entity.Comment;
import uz.app.pcmarket.payload.req.CommentReqDTO;

import java.util.List;

public interface CommentService {
    Comment addComment(Long userId, Long productId, CommentReqDTO commentReqDTO);
    List<Comment> getCommentsByProductId(Long productId);
    boolean hasPurchasedProduct(Long userId, Long productId);
}
