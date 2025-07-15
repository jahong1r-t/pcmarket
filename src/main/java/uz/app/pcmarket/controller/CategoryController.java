package uz.app.pcmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.app.pcmarket.payload.req.CategoryReqDTO;

@Controller
public class CategoryController {
    @PostMapping("add")
    public String addCategory(@ModelAttribute CategoryReqDTO categoryReqDTO) {

        return null;
    }
}
