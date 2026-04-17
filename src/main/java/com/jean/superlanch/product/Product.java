package com.jean.superlanch.product;

import com.jean.superlanch.category.Category;
import com.jean.superlanch.common.exception.BusinessException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "addon_allowed", nullable = false)
    private boolean addonAllowed;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    protected Product() {
    }

    public static Product create(String name, BigDecimal price, Category category, boolean addonAllowed) {
        validate(name, price, category);

        Product product = new Product();
        product.name = name;
        product.price = price;
        product.category = category;
        product.addonAllowed = addonAllowed;

        return product;
    }

    public void update(String name, BigDecimal price, Category category){
        validate(name, price, category);
        this.name = name;
        this.price = price;
        this.category = category;

    }

    public void allowAddons(){
        addonAllowed = true;
    }

    public void disAllowAddons(){
        addonAllowed = false;
    }

    public void changePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.signum() <= 0) {
            throw new BusinessException("Preço inválido");
        }
        this.price = newPrice;
    }

    private static void validate(String name, BigDecimal price, Category category) {
        if (name == null || name.isBlank()) {
            throw new BusinessException("Nome é obrigatório");
        }

        if (price == null || price.signum() <= 0) {
            throw new BusinessException("Preço inválido");
        }

        if (category == null) {
            throw new BusinessException("Categoria obrigatória");
        }
    }
}
