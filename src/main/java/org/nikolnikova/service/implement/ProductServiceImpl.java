package org.nikolnikova.service.implement;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.nikolnikova.entity.Product;
import org.nikolnikova.repository.ProductDAO;
import org.nikolnikova.service.interfaces.ProductService;
import org.nikolnikova.service.util.EntityMapper;
import org.nikolnikova.service.util.service.ProductUtil;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final EntityMapper entityMapper;
    private final ProductDAO productDAO;
    private final ProductUtil productUtil;

    @Transactional
    public Product create(Product product) {

        UUID id = UUID.randomUUID();
        productDAO.save(entityMapper.toEntity(id, product));

        return get(id);
    }

    @Override
    public Collection<Product> get() {
        return entityMapper.toResponse((Collection<Product>) productDAO.findAll());
    }

    @Override
    public Collection<Product> get(String searchTerm) {
        return entityMapper.toResponse(productDAO.findByNameContainingIgnoreCase(searchTerm));
    }

    @Override
    public Collection<Product> get(Double price, String sign) {
        return entityMapper.toResponse((sign.equals("=")) ? productDAO.findByPrice(price)
                : (sign.equals("<") ? productDAO.findByPriceLessThan(price)
                : productDAO.findByPriceGreaterThan(price)));
    }

    @Override
    public Collection<Product> get(Double minPrice, Double maxPrice) {
        return entityMapper.toResponse(productDAO.findByPriceBetween(minPrice, maxPrice));
    }

    @Override
    public Collection<Product> get(Boolean inStock) {
        return entityMapper.toResponse((inStock) ? productDAO.findByInStockTrue() : productDAO.findByInStockFalse());
    }

    public Product get(UUID id) {
        return entityMapper.toResponse(productUtil.getEntity(id));
    }

    public Collection<Product> getSortByPrice(Boolean ascending) {
        return entityMapper.toResponse((ascending) ? productDAO.findAllByOrderByPriceAsc()
                : productDAO.findAllByOrderByPriceDesc());
    }

    public Collection<Product> getSortByPrice(Boolean ascending, Integer limit) {
        return entityMapper.toResponse((ascending) ? productDAO.findFirstByRowsByOrderByPriceAscLimit(limit)
                : productDAO.findFirstByRowsOrderByPriceDescLimit(limit));
    }

    public Collection<Product> getSortByName(Boolean ascending) {
        return entityMapper.toResponse((ascending) ? productDAO.findAllByOrderByNameAsc()
                : productDAO.findAllByOrderByNameDesc());
    }

    public Collection<Product> getSortByName(Boolean ascending, Integer limit) {
        return entityMapper.toResponse((ascending) ? productDAO.findFirstRowsByOrderByNameAscLimit(limit)
                : productDAO.findFirstByRowsByOrderByNameDescLimit(limit));
    }

    @Transactional
    public Product update(UUID id, Product updatedProduct) {

        if (productUtil.getEntity(id) != null) {
            productDAO.save(entityMapper.toEntity(id, updatedProduct));
        }

        return get(id);
    }

    @Transactional
    public Boolean delete(UUID id) {

        if (productUtil.getEntity(id) != null) {
            productDAO.deleteById(id);
        }

        return !productDAO.existsById(id);
    }
}