package uz.app.pcmarket.service;

import uz.app.pcmarket.entity.Cart;
import uz.app.pcmarket.payload.req.AddToCartDTO;

public interface CartService {
    Cart addToCart(Long userId, AddToCartDTO addToCartDTO);
    Cart removeFromCart(Long userId, Long productId);
    Cart getCart(Long userId);
    void clearCart(Long userId);
}