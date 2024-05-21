package com.example.tables.mappers;

import com.example.tables.dto.TableDTO;
import com.example.tables.entity.Table;
import lombok.Lombok;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TableMapper {

    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "brand", target = "brand"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "material", target = "material"),
            @Mapping(source = "size", target = "size")
    })
    Table toEntity(TableDTO tableDTO);

    TableDTO toDTO(Table table);

    List<TableDTO> toDTOList(List<Table> tables);
}
