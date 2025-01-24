package org.nikolnikova.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nikolnikova.entity.Product;
import org.nikolnikova.service.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<Product>> get(
            @RequestParam(value = "search_term", required = false) String searchTerm) {
        return new ResponseEntity<>(
                (searchTerm != null) ? productService.get(searchTerm) : productService.get(),
                HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<Collection<Product>> get(
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "sign", required = false) String sign,
            @RequestParam(value = "min_price", required = false) Double minPrice,
            @RequestParam(value = "max_price", required = false) Double maxPrice) {

        return new ResponseEntity<>(
                (price != null && sign != null) ? productService.get(price, sign)
                        : (minPrice != null && maxPrice != null) ? productService.get(minPrice, maxPrice)
                        : productService.get(),
                HttpStatus.OK);
    }

    @GetMapping("/in_stock")
    public ResponseEntity<Collection<Product>> get(@RequestParam(value = "in_stock") Boolean inStock) {
        return new ResponseEntity<>(productService.get(inStock), HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<Collection<Product>> getSort(
            @RequestParam(value = "sorting_parameter") String name,
            @RequestParam(value = "ascending") Boolean ascending,
            @RequestParam(value = "limit", required = false) Integer limit) {

        return new ResponseEntity<>(
                (name.equals("price")) ? ((limit != null) ? productService.getSortByPrice(ascending, limit)
                        : productService.getSortByPrice(ascending))
                        : ((limit != null) ? productService.getSortByName(ascending, limit)
                        : productService.getSortByName(ascending)),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") UUID id, @Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}