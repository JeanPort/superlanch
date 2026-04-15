package com.jean.superlanch.category;

import com.jean.superlanch.common.AbstractBaseIntegrationTest;
import com.jean.superlanch.common.util.TestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CategoryControllerIT extends AbstractBaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldReturnAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnCategoryById() throws Exception {
        var categoryId = TestConstants.DEFAULT_CATEGORY_ID;

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{categoryId}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryId));
    }

    @Test
    void shouldReturn404WhenCategoryNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories/{categoryId}", TestConstants.DEFAULT_CATEGORY_ID_INVALID))
                .andExpect(status().isNotFound());
    }
}
