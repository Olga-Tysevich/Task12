package com.example.tables.service;

import com.example.tables.entity.Table_;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static com.example.tables.utils.Constants.FIRST_PAGE;

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

    public String getNextSortDir(String sortDir) {
        return Optional.ofNullable(sortDir)
                .filter(sd -> Sort.Direction.ASC.name().equals(sortDir))
                .map(sd -> Sort.Direction.DESC.name())
                .orElse(Sort.Direction.ASC.name());
    }


}
