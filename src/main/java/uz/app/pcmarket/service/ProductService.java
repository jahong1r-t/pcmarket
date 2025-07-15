package uz.app.pcmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.pcmarket.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

}
