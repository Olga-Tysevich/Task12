package com.example.tables.utils;

import java.util.List;

public class MockConstant {

    public static final List<String> BRANDS = List.of("CantoriTestBrand", "AlivarTestBrand");

    public static final List<Long> SIZES = List.of(55L, 48L);

    public static final Long FIRST_TABLE_ID = 1L;

    public static final Long TABLE_ID_FOR_SEARCH = 2L;

    public static final int EMPTY_LIST = 0;

    public static final int FIRST_PAGE = 1;

    public static final int FIRST_PAGE_SIZE = 5;

    public static final int PAGE_BY_BRAND_SEARCH_SIZE = 1;

    public static final String BRAND_FIELD = "brand";

    public static final String BRAND_FOR_SEARCH = "CantoriTestBrand";

    private MockConstant() {
    }

}
