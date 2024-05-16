package com.example.tables.utils;

import com.example.tables.dto.TableDTO;
import com.example.tables.enums.Color;
import com.example.tables.enums.Material;
import java.util.ArrayList;
import java.util.List;

import static com.example.tables.utils.MockConstant.BRANDS;
import static com.example.tables.utils.MockConstant.SIZES;

public class MockUtils {

    public static TableDTO createTable1() {
        return TableDTO.builder()
                .brand(BRANDS.get(0))
                .size(SIZES.get(0))
                .color(Color.BLACK)
                .material(Material.CHIPBOARD)
                .build();
    }

    public static TableDTO createTable2() {
        return TableDTO.builder()
                .brand(BRANDS.get(1))
                .size(SIZES.get(1))
                .color(Color.WHITE)
                .material(Material.WOOD)
                .build();
    }

    public static List<TableDTO> createTables() {
        List<TableDTO> tableList = new ArrayList<>();
        tableList.add(createTable1());
        tableList.add(createTable2());
        return tableList;
    }
}
