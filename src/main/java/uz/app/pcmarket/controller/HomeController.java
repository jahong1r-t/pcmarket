package uz.app.pcmarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.app.pcmarket.entity.Category;
import uz.app.pcmarket.entity.Product;
import uz.app.pcmarket.repository.CategoryRepository;
import uz.app.pcmarket.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @GetMapping
    private String home(Model model) {
        List<Category> all = categoryRepository.findAll();
        List<Product> products = productRepository.findAll();

        model.addAttribute("categories", all);
        model.addAttribute("products", products);
        return "index";
    }
}
