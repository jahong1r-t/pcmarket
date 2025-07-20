package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.controller.CommentController;
import uz.app.pcmarket.entity.Comment;
import uz.app.pcmarket.payload.req.CommentReqDTO;
import uz.app.pcmarket.payload.resp.CommentRespDTO;
import uz.app.pcmarket.service.CommentService;
import java.util.List;


@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentControllerImpl implements CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public String addComment(@RequestParam Long userId, @RequestParam Long productId, @ModelAttribute CommentReqDTO commentReqDTO) {
        commentService.addComment(userId, productId, commentReqDTO);
        return "redirect:/products/" + productId;
    }

    @GetMapping("/product")
    public String getCommentsByProduct(@RequestParam Long productId, Model model) {
        List<Comment> comments = commentService.getCommentsByProductId(productId);
        List<CommentRespDTO> commentDTOs = comments.stream()
                .map(comment -> CommentRespDTO.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .userName(comment.getUser().getFullName())
                        .createdAt(comment.getCreatedAt().toLocalDate().toString())
                        .build())
                .toList();
        model.addAttribute("comments", commentDTOs);
        model.addAttribute("productId", productId);
        model.addAttribute("commentReqDTO", new CommentReqDTO());
        return "product-comments";
    }
}