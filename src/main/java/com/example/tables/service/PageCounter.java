package com.example.tables.service;

import lombok.experimental.UtilityClass;

import static com.example.tables.utils.Constants.FIRST_PAGE;

@UtilityClass
public class PageCounter {

    public int getMaxPageNumber(long numberOfEntries, int pageNumber, int listSize) {
        int maxPageNumber = (int) Math.ceil(((double) numberOfEntries) / listSize);
        return maxPageNumber == 0? FIRST_PAGE - 1: Math.min(maxPageNumber, pageNumber) - 1;
    }

}
