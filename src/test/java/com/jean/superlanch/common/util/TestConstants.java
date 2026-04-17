package com.jean.superlanch.common.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TestConstants {

    public static final Long DEFAULT_CATEGORY_ID_INVALID = 999L;
    public static final Long DEFAULT_CATEGORY_ID = 1L;
    public static final String DEFAULT_CATEGORY_NAME = "Teste1";
    public static final LocalDateTime DEFAULT_CATEGORY_DATA_CREATION = LocalDateTime.now();

    public static final Long DEFAULT_PRODUCT_ID_INVALID = 999L;
    public static final Long DEFAULT_PRODUCT_ID = 1L;
    public static final String DEFAULT_PRODUCT_NAME = "Product Teste1";
    public static final BigDecimal DEFAULT_PRODUCT_PRICE = BigDecimal.TWO;
    public static final boolean DEFAULT_PRODUCT_ADDONALLOWED = true;
    public static final LocalDateTime DEFAULT_PRODUCT_DATA_CREATION = LocalDateTime.now();
    public static final String UPDATE_PRODUCT_NAME = "update name";
    public static final BigDecimal UPDATE_PRODUCT_PRICE = BigDecimal.TEN;

    public static final String DEFAULT_ADDON_NAME = "ADDON TEST";
    public static final BigDecimal DEFAULT_ADDON_PRICE = BigDecimal.TEN;


    private TestConstants() {
    }
}
