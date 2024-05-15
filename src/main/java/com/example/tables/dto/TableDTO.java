package com.example.tables.dto;

import com.example.tables.enums.Color;
import com.example.tables.enums.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDTO {

    private Long id;

    private Long size;

    private String brand;

    private Color color;

    private Material material;

}
