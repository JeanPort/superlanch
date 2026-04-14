package com.jean.superlanch.common.util;

import com.jean.superlanch.category.Category;

public final class TestDataCreator {

    public static Category createCategory(){
        var category = new Category();
        category.setId(TestConstants.DEFAULT_CATEGORY_ID);
        category.setName(TestConstants.DEFAULT_CATEGORY_NAME);
        category.setCreatedAt(TestConstants.DEFAULT_CATEGORY_DATA_CREATION);
        return category;
    }

    private TestDataCreator() {
    }
}
