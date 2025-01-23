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
        Collection<Product> result = (Collection<Product>) productDAO.findAll();
        return entityMapper.toResponse(result);
    }

    public Product get(UUID id) {
        return entityMapper.toResponse(productUtil.getEntity(id));
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