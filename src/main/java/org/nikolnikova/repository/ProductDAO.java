package org.nikolnikova.repository;

import org.nikolnikova.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface ProductDAO extends CrudRepository<Product, UUID> {

    String SELECT1 = """
            SELECT *
            FROM product
            WHERE LOWER("name") LIKE '%' || LOWER(:search_term) || '%'
            """;

    String SELECT2 = """
            SELECT *
            FROM product
            ORDER BY "name" ASC
            LIMIT :limit
            """;

    String SELECT3 = """
            SELECT *
            FROM product
            ORDER BY "name" DESC
            LIMIT :limit
            """;

    String SELECT4 = """
            SELECT *
            FROM product
            ORDER BY price ASC
            LIMIT :limit
            """;

    String SELECT5 = """
            SELECT *
            FROM product
            ORDER BY price ASC
            LIMIT :limit
            """;

    @Query(value = SELECT1, nativeQuery = true)
    Collection<Product> findByNameContainingIgnoreCase(@Param("search_term") String searchTerm);

    Collection<Product> findByPrice(double price);

    Collection<Product> findByPriceGreaterThan(double price);

    Collection<Product> findByPriceLessThan(double price);

    Collection<Product> findByPriceBetween(double minPrice, double maxPrice);

    Collection<Product> findByInStockTrue();

    Collection<Product> findByInStockFalse();

    Collection<Product> findAllByOrderByNameAsc();

    @Query(value = SELECT2, nativeQuery = true)
    Collection<Product> findFirstRowsByOrderByNameAscLimit(@Param("limit") int limit);

    Collection<Product> findAllByOrderByNameDesc();

    @Query(value = SELECT3, nativeQuery = true)
    Collection<Product> findFirstByRowsByOrderByNameDescLimit(int limit);

    Collection<Product> findAllByOrderByPriceAsc();

    @Query(value = SELECT4, nativeQuery = true)
    Collection<Product> findFirstByRowsByOrderByPriceAscLimit(int limit);

    Collection<Product> findAllByOrderByPriceDesc();

    @Query(value = SELECT5, nativeQuery = true)
    Collection<Product> findFirstByRowsOrderByPriceDescLimit(int limit);
}