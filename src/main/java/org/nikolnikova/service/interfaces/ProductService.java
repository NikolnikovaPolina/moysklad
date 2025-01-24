package org.nikolnikova.service.interfaces;

import org.nikolnikova.entity.Product;

import java.util.*;

public interface ProductService {

    Product create(Product product);

    Collection<Product> get();

    Collection<Product> get(String searchTerm);

    Collection<Product> get(Double price, String sign);

    Collection<Product> get(Double minPrice, Double maxPrice);

    Collection<Product> get(Boolean inStock);

    Collection<Product> getSortByPrice(Boolean ascending);

    Collection<Product> getSortByPrice(Boolean ascending, Integer limit);

    Collection<Product> getSortByName(Boolean ascending);

    Collection<Product> getSortByName(Boolean ascending, Integer limit);

    Product get(UUID id);

    Product update(UUID id, Product updatedProduct);

    Boolean delete(UUID id);
}