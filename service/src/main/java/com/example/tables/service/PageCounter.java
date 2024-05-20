package com.example.tables.service;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PageCounter {

    public int getMaxPageNumber(long numberOfEntries, int pageNumber, int listSize) {
        int maxPageNumber = (int) Math.ceil(((double) numberOfEntries) / listSize);
        return maxPageNumber == 0 ? 0 : Math.min(maxPageNumber, pageNumber) - 1;
    }

}
