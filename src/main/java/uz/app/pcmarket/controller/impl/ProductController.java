package uz.app.pcmarket.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.pcmarket.entity.*;
import uz.app.pcmarket.payload.req.ProductReqDTO;
import uz.app.pcmarket.payload.req.SearchReqDTO;
import uz.app.pcmarket.payload.resp.ItemRespDTO;
import uz.app.pcmarket.payload.resp.ParameterRespDTO;
import uz.app.pcmarket.payload.resp.ProductRespDTO;
import uz.app.pcmarket.repository.CategoryRepository;
import uz.app.pcmarket.repository.ParameterItemRepository;
import uz.app.pcmarket.repository.ParameterRepository;
import uz.app.pcmarket.repository.ProductRepository;
import uz.app.pcmarket.service.AttachmentService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ParameterRepository parameterRepository;
    private final ParameterItemRepository parameterItemRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final AttachmentService attachmentService;

    @GetMapping
    public String getProducts(Model model) {
        List<Product> all = productRepository.findAll();
        List<ProductRespDTO> products = all.stream()
                .map(product -> ProductRespDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .imageId(product.getImage().getId().toString())
                        .createAt(product.getCreatedAt().toString())
                        .build())
                .toList();

        model.addAttribute("products", products);

        return "/admin/manage-products";
    }

    @GetMapping("/add")
    public String createProduct(@RequestParam(name = "categoryId") Long categoryId, Model model) {
        List<Parameters> parametersByCategoryId = parameterRepository.findParametersByCategory_Id(categoryId);

        List<ParameterRespDTO> list = parametersByCategoryId.stream()
                .map(parameter -> ParameterRespDTO.builder()
                        .id(parameter.getId())
                        .name(parameter.getName())
                        .categoryName(parameter.getCategory().getName())
                        .createAt(parameter.getCreatedAt().toString())
                        .itemDTOs(parameter.getParamItems().stream()
                                .map(paramItem -> ItemRespDTO.builder()
                                        .id(paramItem.getId())
                                        .name(paramItem.getName())
                                        .parameterName(parameter.getName())
                                        .categoryName(parameter.getCategory().getName())
                                        .createAt(paramItem.getCreatedAt().toString())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .toList();

        model.addAttribute("params", list);
        return "/admin/create-product";
    }


    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute ProductReqDTO productReqDTO) {
        Attachment upload = attachmentService.upload(productReqDTO.getImage());

        List<ParamItem> allById = parameterItemRepository.findAllById(productReqDTO.getItemIds());

        Product build = Product.builder()
                .name(productReqDTO.getName())
                .description(productReqDTO.getDescription())
                .price(productReqDTO.getPrice())
                .quantity(productReqDTO.getQuantity())
                .image(upload)
                .category(allById.get(0).getParameters().getCategory())
                .items(allById)
                .build();

        productRepository.save(build);

        return "redirect:/products";
    }

    @GetMapping("/select-category")
    public String selectCategory(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "/admin/select-category";
    }

    @GetMapping("/params/{categoryId}")
    @ResponseBody
    public List<ParameterRespDTO> getParamsByCategory(@PathVariable Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();

        return category.getFilters().stream().map(param -> {
            List<ItemRespDTO> itemDTOs = param.getParamItems().stream()
                    .map(item -> ItemRespDTO.builder()
                            .id(item.getId())
                            .name(item.getName())
                            .build())
                    .toList();

            return ParameterRespDTO.builder()
                    .id(param.getId())
                    .name(param.getName())
                    .itemDTOs(itemDTOs)
                    .build();
        }).toList();
    }

    @PostMapping("/save")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Long categoryId,
                              @RequestParam List<Long> selectedItemIds) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(categoryRepository.findById(categoryId).orElseThrow());
        productRepository.save(product);
        List<ParamItem> selectedItems = parameterItemRepository.findAllById(selectedItemIds);
        product.setItems(selectedItems);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        List<Parameters> parametersByCategoryId = parameterRepository.findParametersByCategory_Id(id);

        List<ParameterRespDTO> list = parametersByCategoryId.stream()
                .map(parameter -> ParameterRespDTO.builder()
                        .id(parameter.getId())
                        .name(parameter.getName())
                        .categoryName(parameter.getCategory().getName())
                        .createAt(parameter.getCreatedAt().toString())
                        .itemDTOs(parameter.getParamItems().stream()
                                .map(paramItem -> ItemRespDTO.builder()
                                        .id(paramItem.getId())
                                        .name(paramItem.getName())
                                        .parameterName(parameter.getName())
                                        .categoryName(parameter.getCategory().getName())
                                        .createAt(paramItem.getCreatedAt().toString())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .toList();

        List<Product> allByCategoryId = productRepository.findAllByCategory_Id(id);
        List<ProductRespDTO> products = allByCategoryId.stream()
                .map(product -> ProductRespDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .imageId(product.getImage().getId().toString())
                        .createAt(product.getCreatedAt().toString())
                        .build())
                .toList();

        model.addAttribute("params", list);
        model.addAttribute("products", products);
        model.addAttribute("categoryId", id);


        return "products";
    }


    @PostMapping("/search")
    public String searchProducts(@Valid @ModelAttribute() SearchReqDTO searchReqDTO, Model model) {

        List<Product> products;

        if (searchReqDTO.getItemIds() != null && !searchReqDTO.getItemIds().isEmpty()) {
            products = productRepository.findByItemIdsAndPriceBetween(
                    searchReqDTO.getItemIds(),
                    searchReqDTO.getMinPrice(),
                    searchReqDTO.getMaxPrice()
            );
        } else {
            products = productRepository.findByCategoryAndPriceBetween(searchReqDTO.getCategoryId(),
                    searchReqDTO.getMinPrice(),
                    searchReqDTO.getMaxPrice());
        }

        List<Parameters> parametersByCategoryId = parameterRepository.findParametersByCategory_Id(searchReqDTO.getCategoryId());
        List<ParameterRespDTO> listParam = parametersByCategoryId.stream()
                .map(parameter -> ParameterRespDTO.builder()
                        .id(parameter.getId())
                        .name(parameter.getName())
                        .categoryName(parameter.getCategory().getName())
                        .createAt(parameter.getCreatedAt().toString())
                        .itemDTOs(parameter.getParamItems().stream()
                                .map(paramItem -> ItemRespDTO.builder()
                                        .id(paramItem.getId())
                                        .name(paramItem.getName())
                                        .parameterName(parameter.getName())
                                        .categoryName(parameter.getCategory().getName())
                                        .createAt(paramItem.getCreatedAt().toString())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .toList();

        List<ProductRespDTO> list = products.stream().map(product -> ProductRespDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .imageId(product.getImage().getId().toString())
                        .createAt(product.getCreatedAt().toString())
                        .build())
                .toList();


        model.addAttribute("params", listParam);
        model.addAttribute("products", list);
        model.addAttribute("categoryId", searchReqDTO.getCategoryId());

        return "products";
    }
}
