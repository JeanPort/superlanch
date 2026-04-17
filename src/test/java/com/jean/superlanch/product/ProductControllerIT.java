package com.jean.superlanch.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.superlanch.category.Category;
import com.jean.superlanch.category.CategoryRepository;
import com.jean.superlanch.common.AbstractBaseIntegrationTest;
import com.jean.superlanch.common.util.TestConstants;
import com.jean.superlanch.common.util.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ProductControllerIT extends AbstractBaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldCreateProductSuccessfully() throws Exception {
        var request = TestDataCreator.createProductRequest();

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.price").value(request.price()));
    }

    @Test
    void shouldReturn404WhenCategoryNotFound() throws Exception {
        var request = new CreateProductRequest(
                TestConstants.DEFAULT_PRODUCT_NAME,
                TestConstants.DEFAULT_PRODUCT_PRICE,
                TestConstants.DEFAULT_PRODUCT_ADDONALLOWED,
                TestConstants.DEFAULT_CATEGORY_ID_INVALID
        );

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenPriceIsInvalid() throws Exception {
        var request = new CreateProductRequest(
                TestConstants.DEFAULT_PRODUCT_NAME,
                BigDecimal.ZERO,
                TestConstants.DEFAULT_PRODUCT_ADDONALLOWED,
                TestConstants.DEFAULT_CATEGORY_ID
        );

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenNameIsInvalid() throws Exception {
        var request = new CreateProductRequest(
                "",
                TestConstants.DEFAULT_PRODUCT_PRICE,
                TestConstants.DEFAULT_PRODUCT_ADDONALLOWED,
                TestConstants.DEFAULT_CATEGORY_ID
        );

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnProductDetailsWithCategory() throws Exception {
        var productId = TestConstants.DEFAULT_PRODUCT_ID;

        mockMvc.perform(get("/products/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId));
    }

    @Test
    void shouldReturn404WhenIdNotValidNotFound() throws Exception {
        var productId = TestConstants.DEFAULT_PRODUCT_ID_INVALID;

        mockMvc.perform(get("/products/{productId}", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateProductSuccessfully() throws Exception {
        var category = new Category();
        category.setName("CategoriaTest");
        category = categoryRepository.save(category);
        var newCategory = new Category();
        newCategory.setName("Categoria de update");
        newCategory = categoryRepository.save(newCategory);
        var product = Product.create("Novo produto", BigDecimal.TEN, category, true);
        product = repository.save(product);

        var request = new UpdateProductRequest("Nome atualizado", BigDecimal.TWO, newCategory.getId());

        mockMvc.perform(put("/products/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.price").value(request.price()));

        var updated = repository.findById(product.getId()).orElseThrow();

        Assertions.assertEquals(request.name(), updated.getName());
        Assertions.assertEquals(request.categoryId(), updated.getCategory().getId());
    }

    @Test
    void updateShouldReturn404WhenProductNotFound() throws Exception {
        var request = TestDataCreator.createUpdateProductRequest();

        mockMvc.perform(put("/products/{id}", TestConstants.DEFAULT_PRODUCT_ID_INVALID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateShouldReturn404WhenCategoryNotFound() throws Exception {
        var category = new Category();
        category.setName("CategoriaTest");
        category = categoryRepository.save(category);

        var product = Product.create("Novo produto", BigDecimal.TEN, category, true);
        product = repository.save(product);

        var request = new UpdateProductRequest("Novo nome", BigDecimal.ONE, TestConstants.DEFAULT_CATEGORY_ID_INVALID);

        mockMvc.perform(put("/products/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
}
