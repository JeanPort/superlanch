package com.jean.superlanch.category;

import com.jean.superlanch.common.AbstractBaseIntegrationTest;
import com.jean.superlanch.common.util.TestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerIT extends AbstractBaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository repository;

    @Test
    void shouldReturnAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnCategoryById() throws Exception {
        var categoryToSave = new Category();
        categoryToSave.setName(TestConstants.DEFAULT_CATEGORY_NAME);
        categoryToSave = repository.save(categoryToSave);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{categoryId}", categoryToSave.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldReturn404WhenCategoryNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories/{categoryId}", TestConstants.DEFAULT_CATEGORY_ID_INVALID))
                .andExpect(status().isNotFound());
    }
}
