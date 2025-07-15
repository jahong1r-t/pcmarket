package uz.app.pcmarket.service;

import uz.app.pcmarket.entity.Category;
import uz.app.pcmarket.payload.req.CategoryReqDTO;
import java.util.List;

public interface CategoryService {

    Category addCategory(CategoryReqDTO categoryReqDTO);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, CategoryReqDTO categoryReqDTO);
    void deleteCategory(Long id);
}
