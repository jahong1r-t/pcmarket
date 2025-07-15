package uz.app.pcmarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.entity.Product;
import uz.app.pcmarket.repository.ParameterItemRepository;
import uz.app.pcmarket.repository.ParameterRepository;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ParameterRepository parameterRepository;
    private final ParameterItemRepository parameterItemRepository;

    @GetMapping("/{id}")
    public String index(@PathVariable Long id, Model model) {


        return "products";
    }

    @PostMapping
    public String getByFilter(@ModelAttribute Product product) {

        return "redirect:/products/1";
    }
}
