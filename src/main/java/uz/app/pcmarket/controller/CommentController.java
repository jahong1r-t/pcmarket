package uz.app.pcmarket.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.payload.req.CommentReqDTO;

public interface CommentController {
    String addComment(@RequestParam Long userId, @RequestParam Long productId, @ModelAttribute CommentReqDTO commentReqDTO);
    String getCommentsByProduct(@RequestParam Long productId, Model model);
}