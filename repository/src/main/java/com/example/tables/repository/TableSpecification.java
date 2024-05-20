package com.example.tables.repository;

import com.example.tables.entity.Color;
import com.example.tables.entity.Material;
import com.example.tables.entity.Table;
import com.example.tables.entity.Table_;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class TableSpecification {
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";

    public static Specification<Table> search(String keyword) {
        return (Specification<Table>) (root, query, cb) -> {
            if (StringUtils.isBlank(keyword)) {
                return cb.conjunction();
            }

            String pattern = String.format(LIKE_QUERY_PATTERN, keyword);

            if (StringUtils.isNumeric(keyword)) {
                return cb.like(root.get(Table_.SIZE).as(String.class), pattern.toLowerCase());
            }

            if (EnumUtils.isValidEnumIgnoreCase(Color.class, keyword)) {
                return cb.equal(root.get(Table_.COLOR), Color.valueOf(keyword));
            }

            if (EnumUtils.isValidEnumIgnoreCase(Material.class, keyword)) {
                return cb.equal(root.get(Table_.MATERIAL), Material.valueOf(keyword));
            }

            return cb.like(cb.lower(root.get(Table_.BRAND)), pattern.toLowerCase());
        };
    }

}
