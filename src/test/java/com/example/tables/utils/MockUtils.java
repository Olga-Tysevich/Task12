package com.example.tables.utils;

import com.example.tables.dto.TableDTO;
import com.example.tables.entity.Table;
import com.example.tables.enums.Color;
import com.example.tables.enums.Material;

import java.util.List;

import static com.example.tables.utils.MockConstant.BRANDS;
import static com.example.tables.utils.MockConstant.SIZES;

public class MockUtils {

    public static TableDTO createTable() {
        return TableDTO.builder()
                .brand(BRANDS.get(0))
                .size(SIZES.get(0))
                .color(Color.BLACK)
                .material(Material.CHIPBOARD)
                .build();
    }

    public static void searchAssert(List<TableDTO> list, Table table) {

//        Assert.assertEquals("Color not assert", list.get(0).getColor(), table.getColor());
//        Assert.assertEquals("Brand not assert", list.get(0).getBrand(), table.getBrand());
//        Assert.assertEquals("Material not assert", list.get(0).getMaterial(), table.getMaterial());
    }

}
