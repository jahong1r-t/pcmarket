package uz.app.pcmarket.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.payload.req.AddToCartDTO;

public interface CartController {
    String addToCart(@ModelAttribute AddToCartDTO addToCartDTO, @RequestParam Long userId);
    String removeFromCart(@RequestParam Long userId, @RequestParam Long productId);
    String getCart(@RequestParam Long userId, Model model);
    String clearCart(@RequestParam Long userId);
}