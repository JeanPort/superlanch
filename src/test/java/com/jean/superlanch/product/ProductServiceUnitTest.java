package com.jean.superlanch.product;

import com.jean.superlanch.category.CategoryNotFoundException;
import com.jean.superlanch.category.CategoryService;
import com.jean.superlanch.common.exception.BusinessException;
import com.jean.superlanch.common.util.TestConstants;
import com.jean.superlanch.common.util.TestDataCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ProductServiceUnitTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryService categoryService;

    private ProductService victim;

    @BeforeEach
    void setUp(){
        victim = new ProductService(categoryService, repository);
    }

    @Test
    void shouldCreateProductSuccessfully() {
        var category = TestDataCreator.createCategory();
        var request = TestDataCreator.createProductRequest();

        Mockito.when(categoryService.findByIdOrThrow(request.categoryId()))
                .thenReturn(category);

        Mockito.when(repository.save(Mockito.any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var response = victim.create(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(request.name(), response.name());
        Assertions.assertEquals(request.price(), response.price());

        Mockito.verify(repository).save(Mockito.any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        var request = TestDataCreator.createProductRequest();

        Mockito.when(categoryService.findByIdOrThrow(request.categoryId()))
                .thenThrow(CategoryNotFoundException.class);

        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> victim.create(request));

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsInvalid() {

        var category = TestDataCreator.createCategory();
        var request = new CreateProductRequest(
                TestConstants.DEFAULT_PRODUCT_NAME,
                BigDecimal.ZERO,
                TestConstants.DEFAULT_PRODUCT_ADDONALLOWED,
                category.getId());

        Mockito.when(categoryService.findByIdOrThrow(1L))
                .thenReturn(category);

        Assertions.assertThrows(BusinessException.class,
                () -> victim.create(request));

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldThrowExceptionWhenNameIsInvalid() {
        var category = TestDataCreator.createCategory();
        var request = new CreateProductRequest(
                "",
                TestConstants.DEFAULT_PRODUCT_PRICE,
                TestConstants.DEFAULT_PRODUCT_ADDONALLOWED,
                category.getId());

        Mockito.when(categoryService.findByIdOrThrow(request.categoryId()))
                .thenReturn(category);

        Assertions.assertThrows(BusinessException.class,
                () -> victim.create(request));

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }
}
