package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.entity.Category;
import uz.app.pcmarket.entity.Parameters;
import uz.app.pcmarket.payload.req.ParameterReqDTO;
import uz.app.pcmarket.payload.resp.CategoryRespDTO;
import uz.app.pcmarket.payload.resp.ParameterRespDTO;
import uz.app.pcmarket.repository.CategoryRepository;
import uz.app.pcmarket.repository.ParameterRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/parameter")
@RequiredArgsConstructor
public class ParameterController {
    private final CategoryRepository categoryRepository;
    private final ParameterRepository parameterRepository;

    @GetMapping
    public String parameter(Model model) {
        List<Category> all = categoryRepository.findAll();

        List<CategoryRespDTO> list = all.stream().map(c -> CategoryRespDTO.builder()
                .id(c.getId().toString())
                .name(c.getName())
                .createAt(c.getCreatedAt().toLocalDate().toString())
                .build()
        ).toList();

        List<Parameters> allParameter = parameterRepository.findAll();

        List<ParameterRespDTO> resp = allParameter.stream().map(p -> ParameterRespDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .createAt(p.getCreatedAt().toLocalDate().toString())
                .categoryName(p.getCategory().getName())
                .build()
        ).toList();

        model.addAttribute("parameters", resp);
        model.addAttribute("categories", list);

        return "admin/create-parameter";
    }

    @PostMapping("/add")
    public String addParameter(@ModelAttribute ParameterReqDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found"));

        Parameters build = Parameters.builder()
                .name(dto.getName())
                .category(category)
                .build();

        parameterRepository.save(build);

        return "redirect:/parameter";
    }

    @GetMapping("/delete/{id}")
    public String deleteParameter(@PathVariable Long id) {
        parameterRepository.deleteById(id);
        return "redirect:/parameter";
    }
}
