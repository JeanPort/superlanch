package com.jean.superlanch.common.util;

import com.jean.superlanch.category.Category;
import com.jean.superlanch.product.CreateProductRequest;

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

    private TestDataCreator() {
    }
}
