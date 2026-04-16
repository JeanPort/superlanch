package com.jean.superlanch.product;

import java.math.BigDecimal;

public record ProductFullResponse(
        Long id,
        String name,
        BigDecimal price,
        String categoryName
) {

    public static ProductFullResponse toProductFullResponse(Product product){
        return new ProductFullResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }
}
