package com.jean.superlanch.product;

import com.jean.superlanch.common.exception.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {

    private static final String MSG_PRODUCT_NOT_FOUND = "Product not found";

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException() {
        super(MSG_PRODUCT_NOT_FOUND);
    }
}
