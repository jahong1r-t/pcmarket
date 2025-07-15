package uz.app.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.pcmarket.entity.ParamItem;

import java.util.List;

public interface ParameterItemRepository extends JpaRepository<ParamItem, Long> {
    List<ParamItem> findParamItemByParameters_Category_Id(Long id);
}
