package uz.app.pcmarket.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.payload.req.CategoryReqDTO;

@RequestMapping("/category")
public interface CategoryController {
    String addCategory(@ModelAttribute CategoryReqDTO categoryReqDTO);
    String getAllCategories(Model model);
    String getCategoryById(@PathVariable Long id, Model model);
    String updateCategory(@PathVariable Long id, @ModelAttribute CategoryReqDTO categoryReqDTO);
    String deleteCategory(@PathVariable Long id);
}

