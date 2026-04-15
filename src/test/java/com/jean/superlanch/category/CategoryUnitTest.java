package com.jean.superlanch.category;

import com.jean.superlanch.common.util.TestConstants;
import com.jean.superlanch.common.util.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoryUnitTest {
    @Mock
    private CategoryRepository repository;

    private CategoryService victim;

    @BeforeEach
    void setUp(){
        victim = new CategoryService(repository);
    }

    @Test
    void shouldReturnCategoryWhenExists() {
        var category = TestDataCreator.createCategory();
        Mockito.when(repository.findById(category.getId()))
                .thenReturn(Optional.of(category));

        var result = victim.findByIdOrThrow(category.getId());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(category.getName(), result.getName());
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        var invalidId = TestConstants.DEFAULT_CATEGORY_ID_INVALID;
        Mockito.when(repository.findById(invalidId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> victim.findByIdOrThrow(invalidId));
    }

    @Test
    void shouldReturnCategoryResponseWhenFindById() {
        var category = TestDataCreator.createCategory();
        Mockito.when(repository.findById(category.getId()))
                .thenReturn(Optional.of(category));

        var response = victim.findById(category.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(category.getId(), response.id());
        Assertions.assertEquals(category.getName(), response.name());
    }

    @Test
    void shouldThrowExceptionWhenFindByIdAndCategoryNotFound() {
        var invalidId = TestConstants.DEFAULT_CATEGORY_ID_INVALID;
        Mockito.when(repository.findById(invalidId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> victim.findById(invalidId));
    }

    @Test
    void shouldReturnListOfCategoryResponse() {
        var category = TestDataCreator.createCategory();
        var categories = List.of(category);
        Mockito.when(repository.findAll())
                .thenReturn(categories);

        var result = victim.findAll();

        Assertions.assertEquals(categories.size(), result.size());
        Assertions.assertEquals(category.getId(), result.getFirst().id());
    }

    @Test
    void shouldReturnEmptyListWhenNoCategories() {
        Mockito.when(repository.findAll())
                .thenReturn(Collections.emptyList());

        var result = victim.findAll();

        Assertions.assertTrue(result.isEmpty());
    }
}
