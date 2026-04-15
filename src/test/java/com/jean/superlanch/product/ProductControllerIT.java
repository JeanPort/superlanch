package com.jean.superlanch.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.superlanch.common.AbstractBaseIntegrationTest;
import com.jean.superlanch.common.util.TestConstants;
import com.jean.superlanch.common.util.TestDataCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
