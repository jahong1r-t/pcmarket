package uz.app.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.Cart;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}