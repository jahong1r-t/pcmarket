package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.controller.CategoryController;
import uz.app.pcmarket.payload.req.CategoryReqDTO;
import uz.app.pcmarket.service.CategoryService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories" )
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @PostMapping("add")
    public String addCategory(@ModelAttribute CategoryReqDTO categoryReqDTO) {
        categoryService.addCategory(categoryReqDTO);
        return "redirect:/categories";
    }

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "list";
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
