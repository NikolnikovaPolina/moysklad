package org.nikolnikova.service.interfaces;

import org.nikolnikova.entity.Product;

import java.util.*;

public interface ProductService {

    Product create(Product product);

    Collection<Product> get();

    Product get(UUID id);

    Product update(UUID id, Product updatedProduct);

    Boolean delete(UUID id);
}