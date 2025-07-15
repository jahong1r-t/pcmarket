package uz.app.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
