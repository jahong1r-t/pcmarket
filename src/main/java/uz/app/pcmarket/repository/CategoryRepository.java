package uz.app.pcmarket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
