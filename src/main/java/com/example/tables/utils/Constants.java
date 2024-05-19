package com.example.tables.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final int PAGE_SIZE = 5;
    public static final int FIRST_PAGE = 1;
    public static final String FIND_PATTERN = "/tables/page/%d/?sortField=%s&sortDir=%s&keyword=%s";
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";
    public static final String TABLES_PAGE_REDIRECT = "redirect:/tables";
    public static final String TABLE_ID = "id";
    public static final String SIZE = "size";
    public static final String BRAND = "brand";
    public static final String COLOR = "color";
    public static final String MATERIAL = "material";
    public static final String ASC_SORT = "asc";
    public static final String DESC_SORT = "desc";
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE = "pageForDisplay";
    public static final String SORT_FIELD = "sortField";
    public static final String KEYWORD = "keyword";
    public static final String SORT_DIR = "sortDir";
    public static final String TABLE_LIST = "tableList";
    public static final String TABLE = "table";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_PAGE = "error";


}
