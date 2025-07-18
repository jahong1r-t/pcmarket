package uz.app.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.app.pcmarket.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_Id(Long categoryId);

    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN p.items i " +
            "WHERE i.id IN :itemIds " +
            "AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByItemIdsAndPriceBetween(@Param("itemIds") List<Long> itemIds,
                                               @Param("minPrice") Double minPrice,
                                               @Param("maxPrice") Double maxPrice);

    @Query("SELECT p FROM Product p " +
            "WHERE p.category.id = :categoryId " +
            "AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByCategoryAndPriceBetween(@Param("categoryId") Long categoryId,
                                                @Param("minPrice") Double minPrice,
                                                @Param("maxPrice") Double maxPrice);


}
