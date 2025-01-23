package org.nikolnikova.service.util;

import org.nikolnikova.entity.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class EntityMapper {
    public Product toResponse(Product product) {
        return new Product(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getInStock()
        );
    }

    public Collection<Product> toResponse(Collection<Product> products) {
       Collection<Product> result = new ArrayList<>();
       products.forEach(product -> result.add(toResponse(product)));
        return result;
    }

    public Product toEntity(UUID id, Product product) {
        return Product.builder()
                .id(id)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .inStock(product.getInStock())
                .build();
    }
}