package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.entity.ParamItem;
import uz.app.pcmarket.entity.Parameters;
import uz.app.pcmarket.payload.req.ItemReqDTO;
import uz.app.pcmarket.payload.resp.ItemRespDTO;
import uz.app.pcmarket.payload.resp.ParameterRespDTO;
import uz.app.pcmarket.repository.ParameterItemRepository;
import uz.app.pcmarket.repository.ParameterRepository;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ParameterRepository parameterRepository;
    private final ParameterItemRepository parameterItemRepository;

    @GetMapping
    public String getItems(Model model) {
        List<ParameterRespDTO> list = parameterRepository.findAll().stream()
                .map(p -> ParameterRespDTO.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .categoryName(p.getCategory().getName())
                        .build()
                ).toList();

        List<ItemRespDTO> items = parameterItemRepository.findAll().stream()
                .map(pi -> ItemRespDTO.builder()
                        .id(pi.getId())
                        .name(pi.getName())
                        .parameterName(pi.getParameters().getName())
                        .categoryName(pi.getParameters().getCategory().getName())
                        .build())
                .toList();

        model.addAttribute("params", list);
        model.addAttribute("items", items);

        return "admin/create-item";
    }

    @PostMapping("/add")
    public String createItem(@Validated @ModelAttribute ItemReqDTO itemReqDTO) {
        Parameters parameters = parameterRepository.findById(itemReqDTO.getParamId()).orElseThrow();

        ParamItem build = ParamItem.builder()
                .parameters(parameters)
                .name(itemReqDTO.getName())
                .build();

        parameterItemRepository.save(build);

        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteParameter(@PathVariable Long id) {
        parameterItemRepository.deleteById(id);
        return "redirect:/parameter";
    }
}
