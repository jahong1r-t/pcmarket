package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.controller.CartController;
import uz.app.pcmarket.payload.req.AddToCartDTO;
import uz.app.pcmarket.service.CartService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartControllerImpl implements CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public String addToCart(@ModelAttribute AddToCartDTO addToCartDTO, @RequestParam Long userId) {
        cartService.addToCart(userId, addToCartDTO);
        return "redirect:/cart?userId=" + userId;
    }

    @GetMapping("/remove")
    public String removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeFromCart(userId, productId);
        return "redirect:/cart?userId=" + userId;
    }

    @GetMapping
    public String getCart(@RequestParam Long userId, Model model) {
        model.addAttribute("cart", cartService.getCart(userId));
        return "cart";
    }

    @GetMapping("/clear")
    public String clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return "redirect:/cart?userId=" + userId;
    }
}