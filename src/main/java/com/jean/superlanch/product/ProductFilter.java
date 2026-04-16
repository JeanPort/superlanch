package com.jean.superlanch.product;

public record ProductFilter(String name, Long categoryId) {

    public boolean hasName(){
        return name != null && !name.isBlank();
    }

    public boolean hasCategory(){
        return categoryId != null;
    }
}
