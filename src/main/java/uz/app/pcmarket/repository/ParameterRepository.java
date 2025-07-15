package uz.app.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.Parameters;

import java.util.List;

public interface ParameterRepository extends JpaRepository<Parameters, Long> {
    List<Parameters> findParametersByCategory_Id(Long categoryId);
}
