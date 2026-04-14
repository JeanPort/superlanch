package com.jean.superlanch.category;

import com.jean.superlanch.common.exception.ResourceNotFoundException;

public class CategoryNotFoundException extends ResourceNotFoundException {

    private static final String MSG_CATEGORY_NOT_FOUND = "Category not found";

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super(MSG_CATEGORY_NOT_FOUND);
    }
}
