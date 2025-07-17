package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.controller.CategoryController;
import uz.app.pcmarket.entity.Category;
import uz.app.pcmarket.payload.req.CategoryReqDTO;
import uz.app.pcmarket.payload.resp.CategoryRespDTO;
import uz.app.pcmarket.service.CategoryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public String addCategory(@ModelAttribute CategoryReqDTO categoryReqDTO) {
        categoryService.addCategory(categoryReqDTO);
        return "redirect:/categories";
    }

    /// 100 done
    @GetMapping
    public String getAllCategories(Model model) {
        List<Category> allCategories = categoryService.getAllCategories();

        List<CategoryRespDTO> list = allCategories.stream()
                .map(c -> CategoryRespDTO.builder()
                        .id(c.getId().toString())
                        .name(c.getName())
                        .createAt(c.getCreatedAt().toLocalDate().toString())
                        .build()).toList();

        model.addAttribute("categories", list);
        return "admin/create-category";
    }


    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "view";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryReqDTO categoryReqDTO) {
        categoryService.updateCategory(id, categoryReqDTO);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("categoryReqDTO", new CategoryReqDTO());
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        model.addAttribute("categoryReqDTO", new CategoryReqDTO());
        return "edit";
    }
}
