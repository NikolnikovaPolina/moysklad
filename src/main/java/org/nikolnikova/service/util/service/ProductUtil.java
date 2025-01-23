package org.nikolnikova.service.util.service;

import lombok.AllArgsConstructor;
import org.nikolnikova.entity.Product;
import org.nikolnikova.exception.ProductNotFoundException;
import org.nikolnikova.repository.ProductDAO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ProductUtil {

    private final ProductDAO productDAO;

    public Product getEntity(UUID id) {
        return productDAO.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }
}