package uz.app.pcmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.pcmarket.entity.Cart;
import uz.app.pcmarket.entity.CartItem;
import uz.app.pcmarket.entity.Product;
import uz.app.pcmarket.entity.User;
import uz.app.pcmarket.payload.req.AddToCartDTO;
import uz.app.pcmarket.repository.CartItemRepository;
import uz.app.pcmarket.repository.CartRepository;
import uz.app.pcmarket.repository.ProductRepository;
import uz.app.pcmarket.repository.UserRepository;
import uz.app.pcmarket.service.CartService;
import java.util.ArrayList;

@Service("cartService")
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart addToCart(Long userId, AddToCartDTO addToCartDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(addToCartDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(Cart.builder().user(user).cartItems(new ArrayList<>()).build());

        // Проверяем, есть ли продукт уже в корзине
        CartItem existingItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(addToCartDTO.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Увеличиваем количество, если продукт уже в корзине
            existingItem.setQuantity(existingItem.getQuantity() + addToCartDTO.getQuantity());
            cartItemRepository.save(existingItem);
        } else {
            // Добавляем новый элемент в корзину
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(addToCartDTO.getQuantity())
                    .build();
            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        CartItem itemToRemove = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

        cart.getCartItems().remove(itemToRemove);
        cartItemRepository.delete(itemToRemove);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository
                .findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}