package com.example.tables.service;

import org.springframework.data.domain.Page;
import com.example.tables.dto.TableDTO;

import java.util.List;

public interface TableService {

    Long createOrUpdateTable(TableDTO tableDTO);

    boolean deleteTable(Long id);

    TableDTO findById(Long id);

    List<TableDTO> findAll();

    Page<TableDTO> findForPage(int pageNumber, int pageSize, String sortField, String sortDir, String keyword);

}
