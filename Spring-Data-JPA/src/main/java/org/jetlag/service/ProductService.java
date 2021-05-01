package org.jetlag.service;

import lombok.RequiredArgsConstructor;
import org.jetlag.domain.entity.Product;
import org.jetlag.domain.entity.QProduct;
import org.jetlag.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        QProduct product = QProduct.product;
        Iterable<Product> result = productRepository.findAll(
                product.name.contains("toy").and(product.price.between(10000, 20000))
        );

        return StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
    }
}
