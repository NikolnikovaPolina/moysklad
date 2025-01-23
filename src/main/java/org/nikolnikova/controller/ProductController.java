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
    public ResponseEntity<Collection<Product>> get() {
        return new ResponseEntity<>(productService.get(), HttpStatus.OK);
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