package com.jean.superlanch.addon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateAddonRequest(
        @NotBlank String name,
        @NotNull @Positive BigDecimal price) {
}
