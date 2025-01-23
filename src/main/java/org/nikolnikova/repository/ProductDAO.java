package org.nikolnikova.repository;

import org.nikolnikova.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDAO extends CrudRepository<Product, UUID> {
}