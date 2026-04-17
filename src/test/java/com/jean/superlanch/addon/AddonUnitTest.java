package com.jean.superlanch.addon;

import com.jean.superlanch.common.exception.BusinessException;
import com.jean.superlanch.common.util.TestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddonUnitTest {
    @Mock
    private AddonRepository repository;

    private AddonService service;

    @BeforeEach
    void setUp() {
        service = new AddonService(repository);
    }

    @Test
    @DisplayName("create: deve lançar exceção quando nome já existe")
    void create_shouldThrow_whenNameAlreadyExists() {
        var request = TestDataCreator.createAddonRequest();
        when(repository.existsByName(request.name())).thenReturn(true);

        assertThatThrownBy(() -> service.create(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Já existe um addon com este nome");

        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("create: deve salvar quando nome não existe")
    void create_shouldSave_whenNameIsAvailable() {
        var request = TestDataCreator.createAddonRequest();

        when(repository.existsByName(request.name())).thenReturn(false);
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var response = service.create(request); // única chamada

        assertThat(response.name()).isEqualTo(request.name());
        assertThat(response.price()).isEqualByComparingTo(request.price());
        verify(repository).save(any(Addon.class));
        verify(repository, never()).existsByNameAndIdNot(any(), any());
    }


    @Test
    @DisplayName("update: deve lançar exceção quando nome pertence a outro addon")
    void update_shouldThrow_whenNameBelongsToAnotherAddon() {
    }

    @Test
    @DisplayName("update: deve atualizar quando nome pertence ao próprio addon")
    void update_shouldUpdate_whenNameBelongsToSameAddon() {
    }

    @Test
    @DisplayName("update: deve atualizar quando nome está disponível")
    void update_shouldUpdate_whenNameIsAvailable() {
    }

    @Test
    @DisplayName("update: nunca deve chamar existsByName sem id")
    void update_shouldNeverCall_existsByName() {
    }
}

