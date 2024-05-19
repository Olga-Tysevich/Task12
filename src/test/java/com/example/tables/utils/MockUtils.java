package com.example.tables.utils;

import com.example.tables.dto.TableDTO;
import com.example.tables.entity.Color;
import com.example.tables.entity.Material;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.tables.utils.MockConstant.*;

public class MockUtils {

    public static TableDTO createTable() {
        return TableDTO.builder()
                .brand(BRAND1)
                .size(SIZE1)
                .color(Color.values()[RANDOM.nextInt(Color.values().length)])
                .material(Material.values()[RANDOM.nextInt(Material.values().length)])
                .build();
    }

    public static List<TableDTO> createTables(int listSize) {
        return IntStream.range(0, listSize)
                .mapToObj(i -> createTable())
                .collect(Collectors.toList());
    }
}
