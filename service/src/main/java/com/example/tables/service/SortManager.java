package com.example.tables.service;

import com.example.tables.entity.Table_;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
@UtilityClass
public class SortManager {

    public Sort defineCurrentSortDir(String sortDir, String sortField) {
        if (StringUtils.isNotBlank(sortDir) && StringUtils.isNotBlank(sortField)) {
            return Sort.Direction.ASC.name().equals(sortDir) ?
                    Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();
        } else {
            return Sort.by(Table_.BRAND).ascending();
        }
    }

}
