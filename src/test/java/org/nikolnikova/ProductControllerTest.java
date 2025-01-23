package org.nikolnikova;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.nikolnikova.entity.Product;
import org.nikolnikova.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    @Test
    void createProduct_validProduct_returnsCreated() throws Exception {
        Product product = new Product("Test Product", "Test Description", 10.0, true);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    void createProduct_invalidProduct_returnsBadRequest() throws Exception {
        String longDesc = "a".repeat(4097);
        Product product = new Product("", longDesc, -1.0, true);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name is required"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description must not exceed 4096 characters"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("Price must be greater than or equal to 0"));
    }

    @Test
    void getProductById_existingProduct_returnsProduct() throws Exception {
        Product product = new Product("Test Product 2", "Test Desc 2", 15.5, false);
        productService.create(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Product 2"));
    }

    @Test
    void getProductById_nonExistingProduct_returnsNotFound() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProduct_existingProduct_returnsOk() throws Exception {
        Product initialProduct = new Product("Initial Product", "Initial Description", 5.0);
        productService.create(initialProduct);
        Product updatedProduct = new Product(initialProduct.getId(), "Updated Name", "Updated Description", 10.0, true);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + initialProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Name"));
    }
}