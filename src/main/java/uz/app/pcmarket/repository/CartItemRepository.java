package uz.app.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}