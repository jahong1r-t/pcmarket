package uz.app.pcmarket.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.pcmarket.entity.Category;
import uz.app.pcmarket.payload.req.CategoryReqDTO;
import uz.app.pcmarket.repository.CategoryRepository;
import uz.app.pcmarket.service.CategoryService;

import java.util.List;


@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category addCategory(CategoryReqDTO categoryReqDTO) {
        if (categoryReqDTO.getName() == null || categoryReqDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        Category category = Category
                .builder()
                .name(categoryReqDTO.getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Category with id " + id + " not found. Please check the ID and try again."));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, CategoryReqDTO categoryReqDTO) {
        if (categoryReqDTO.getName() == null || categoryReqDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty or null");
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Category with id " + id + " not found. Please check the ID and try again."));
        category.setName(categoryReqDTO.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }


}
