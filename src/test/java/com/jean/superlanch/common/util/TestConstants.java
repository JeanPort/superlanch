package com.jean.superlanch.common.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TestConstants {

    public static final Long DEFAULT_CATEGORY_ID_INVALID = 100L;
    public static final Long DEFAULT_CATEGORY_ID = 1L;
    public static final String DEFAULT_CATEGORY_NAME = "Teste1";
    public static final LocalDateTime DEFAULT_CATEGORY_DATA_CREATION = LocalDateTime.now();

    public static final Long DEFAULT_PRODUCT_ID_INVALID = 100L;
    public static final Long DEFAULT_PRODUCT_ID = 1L;
    public static final String DEFAULT_PRODUCT_NAME = "Product Teste1";
    public static final BigDecimal DEFAULT_PRODUCT_PRICE = BigDecimal.valueOf(20.00);
    public static final boolean DEFAULT_PRODUCT_ADDONALLOWED = true;
    public static final LocalDateTime DEFAULT_PRODUCT_DATA_CREATION = LocalDateTime.now();

    private TestConstants() {
    }
}
