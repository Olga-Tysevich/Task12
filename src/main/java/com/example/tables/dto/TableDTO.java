package com.example.tables.dto;

import com.example.tables.enums.Color;
import com.example.tables.enums.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDTO {

    private Long id;

    @NotNull(message = "Размер обязателен!")
    private Long size;

    @NotBlank(message = "Бренд обязателен!")
    private String brand;

    private Color color;

    private Material material;

}
