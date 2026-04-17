package com.jean.superlanch.common.util;

import com.jean.superlanch.category.Category;
import com.jean.superlanch.product.CreateProductRequest;
import com.jean.superlanch.product.Product;
import com.jean.superlanch.product.UpdateProductRequest;

public final class TestDataCreator {

    public static Category createCategory(){
        var category = new Category();
        category.setId(TestConstants.DEFAULT_CATEGORY_ID);
        category.setName(TestConstants.DEFAULT_CATEGORY_NAME);
        category.setCreatedAt(TestConstants.DEFAULT_CATEGORY_DATA_CREATION);
        return category;
    }

    public static CreateProductRequest createProductRequest(){
        return new CreateProductRequest(
                TestConstants.DEFAULT_PRODUCT_NAME,
                TestConstants.DEFAULT_PRODUCT_PRICE,
                TestConstants.DEFAULT_PRODUCT_ADDONALLOWED,
                TestConstants.DEFAULT_CATEGORY_ID);
    }

    public static Product createProduct(){
        return Product.create(
                TestConstants.DEFAULT_PRODUCT_NAME,
                TestConstants.DEFAULT_PRODUCT_PRICE,
                createCategory(),
                TestConstants.DEFAULT_PRODUCT_ADDONALLOWED);
    }

    public static UpdateProductRequest createUpdateProductRequest(){
        return new UpdateProductRequest(
                TestConstants.UPDATE_PRODUCT_NAME,
                TestConstants.UPDATE_PRODUCT_PRICE,
                TestConstants.DEFAULT_CATEGORY_ID);
    }

    private TestDataCreator() {
    }
}
