package com.jean.superlanch.addon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.superlanch.common.BaseIntegrationTest;
import com.jean.superlanch.common.util.TestConstants;
import com.jean.superlanch.common.util.TestDataCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddonControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AddonRepository repository;

    @Test
    @DisplayName("POST /addons: deve criar addon e retornar 201 com os dados corretos")
    void create_shouldReturn201_whenRequestIsValid() throws Exception {
        var request = TestDataCreator.createAddonRequest();

        mockMvc.perform(post("/addons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.price").value(request.price()));
    }

    @Test
    @DisplayName("POST /addons: deve retornar 409 quando nome já existe")
    void create_shouldReturn409_whenNameAlreadyExists() throws Exception {
        var addon = repository.save(TestDataCreator.createAddon());

        var request = new CreateAddonRequest(addon.getName(), addon.getPrice());

        mockMvc.perform(post("/addons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("POST /addons: deve retornar 400 quando nome é nulo")
    void create_shouldReturn400_whenNameIsNull() throws Exception {
        var request = new CreateAddonRequest(null, TestConstants.DEFAULT_ADDON_PRICE);

        mockMvc.perform(post("/addons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /addons: deve retornar 400 quando nome é vazio")
    void create_shouldReturn400_whenNameIsBlank() throws Exception {
        var request = new CreateAddonRequest("  ", TestConstants.DEFAULT_ADDON_PRICE);

        mockMvc.perform(post("/addons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /addons: deve retornar 400 quando preço é nulo")
    void create_shouldReturn400_whenPriceIsNull() throws Exception {
        var request = new CreateAddonRequest(TestConstants.DEFAULT_ADDON_NAME, null);

        mockMvc.perform(post("/addons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /addons: deve retornar 400 quando preço é zero")
    void create_shouldReturn400_whenPriceIsZero() throws Exception {
        var request = new CreateAddonRequest(TestConstants.DEFAULT_ADDON_NAME, BigDecimal.ZERO);

        mockMvc.perform(post("/addons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /addons: deve retornar 400 quando preço é negativo")
    void create_shouldReturn400_whenPriceIsNegative() throws Exception {
        var request = new CreateAddonRequest(TestConstants.DEFAULT_ADDON_NAME, new BigDecimal("-5.00"));

        mockMvc.perform(post("/addons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
