package com.example.tables.entity;

import com.example.tables.enums.Color;
import com.example.tables.enums.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long size;

    @Column
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column
    private Material material;


}
