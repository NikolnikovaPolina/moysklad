package org.nikolnikova.service;

import org.jetbrains.annotations.NotNull;
import org.nikolnikova.entity.Product;
import org.nikolnikova.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final Map<UUID, Product> products = new HashMap<>();

    public Product create(@NotNull Product product) {
        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> getProductById(UUID id) {
        Optional<Product> product = Optional.ofNullable(products.get(id));
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product;
    }

    public Optional<Product> updateProduct(UUID id, Product updatedProduct) {
        if (products.containsKey(id)) {
            updatedProduct.setId(id);
            products.put(id, updatedProduct);
            return Optional.of(updatedProduct);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }

    public void deleteProduct(UUID id) {
        if (products.containsKey(id)) {
            products.remove(id);
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }
}