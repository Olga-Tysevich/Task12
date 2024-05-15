package com.example.tables.service;


import com.example.tables.dto.TableDTO;
import com.example.tables.entity.Table;
import com.example.tables.repository.TableRepository;
import com.example.tables.utils.TableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.tables.utils.Constants.PAGE_SIZE;

@Transactional
@Service
public class TableServiceImpl implements TableService {
    private final TableRepository repository;

    @Autowired
    public TableServiceImpl(TableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createOrUpdateTable(TableDTO tableDTO) {
        Table table = TableMapper.INSTANCE.toEntity(tableDTO);
        repository.save(table);
    }

    @Override
    public TableDTO findById(Long id) {
        Table table = repository.getById(id);
        return TableMapper.INSTANCE.toDTO(table);
    }

    @Override
    public void deleteTable(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<TableDTO> findAll() {
        List<Table> result = repository.findAll();
        return TableMapper.INSTANCE.toDTOList(result);
    }

    @Override
    public Page<TableDTO> findForPage(int pageNumber, String sortField, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, sort);
        Page<Table> temp = StringUtils.isNotBlank(keyword)? repository.findForPage(keyword, pageRequest)
                : repository.findAll(pageRequest);
        List<TableDTO> tableDTOList = TableMapper.INSTANCE.toDTOList(temp.getContent());
        return new PageImpl<>(tableDTOList, temp.getPageable(), temp.getTotalElements());
    }

}
