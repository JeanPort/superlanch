package com.jean.superlanch.addon;

import com.jean.superlanch.common.exception.BusinessException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "addon")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Addon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Addon() {
    }

    public static Addon create(String name, BigDecimal price){
        validate(name, price);
        var addon = new Addon();
        addon.name = name;
        addon.price = price;
        return addon;
    }

    public void update(String name, BigDecimal price){
        validate(name, price);
        this.name = name;
        this.price = price;
    }

    private static void validate(String name, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new BusinessException("Nome é obrigatório");
        }

        if (price == null || price.signum() <= 0) {
            throw new BusinessException("Preço inválido");
        }
    }
}
