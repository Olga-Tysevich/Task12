package com.example.tables.utils;

import com.example.tables.dto.TableDTO;
import com.example.tables.entity.Table;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TableMapper {

    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    Table toEntity(TableDTO tableDTO);

    TableDTO toDTO(Table table);

    List<TableDTO> toDTOList(List<Table> tables);
}
