package com.example.tables.service;

import com.example.tables.dto.TableDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TableService {

    Long createOrUpdateTable(TableDTO tableDTO);

    boolean deleteTable(Long id);

    TableDTO findById(Long id);

    List<TableDTO> findAll();

    Page<TableDTO> findForPage(int pageNumber, String sortField, String sortDir, String keyword);

}
