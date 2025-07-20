package uz.app.pcmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.pcmarket.entity.Comment;
import uz.app.pcmarket.entity.Cart;
import uz.app.pcmarket.entity.Order;
import uz.app.pcmarket.entity.Product;
import uz.app.pcmarket.entity.User;
import uz.app.pcmarket.payload.req.CommentReqDTO;
import uz.app.pcmarket.repository.CommentRepository;
import uz.app.pcmarket.repository.OrderRepository;
import uz.app.pcmarket.repository.ProductRepository;
import uz.app.pcmarket.repository.UserRepository;
import uz.app.pcmarket.repository.CartRepository;
import uz.app.pcmarket.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Override
    public Comment addComment(Long userId, Long productId, CommentReqDTO commentReqDTO) {
        if (!hasPurchasedProduct(userId, productId)) {
            throw new IllegalStateException("User has not purchased this product");
        }
        if (commentReqDTO.getContent() == null || commentReqDTO.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Comment comment = Comment.builder()
                .content(commentReqDTO.getContent())
                .user(user)
                .product(product)
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByProductId(Long productId) {
        return commentRepository.findByProductId(productId);
    }

    @Override
    public boolean hasPurchasedProduct(Long userId, Long productId) {
        // Foydalanuvchining savatini topamiz
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        if (cart == null) {
            return false;
        }

        // Savatdagi mahsulotlarni tekshiramiz
        return cart.getCartItems().stream()
                .anyMatch(cartItem -> cartItem.getProduct().getId().equals(productId));
    }
}
