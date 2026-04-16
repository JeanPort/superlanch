package com.jean.superlanch.product;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> withFilter(ProductFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.hasName()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + filter.name().toLowerCase() + "%"
                        )
                );
            }

            if (filter.hasCategory()) {
                predicates.add(
                        cb.equal(root.get("category").get("id"), filter.categoryId())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
