package com.example.tables.service;

import com.example.tables.entity.Table;
import com.example.tables.repository.TableRepository;
import com.example.tables.repository.TableSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.tables.dto.TableDTO;
import com.example.tables.mappers.TableMapper;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final TableRepository repository;

    @Override
    public Long createOrUpdateTable(TableDTO tableDTO) {
        return Optional.ofNullable(tableDTO)
                .map(TableMapper.INSTANCE::toEntity)
                .map(repository::save)
                .map(Table::getId)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public TableDTO findById(Long id) {
        return Optional.ofNullable(id)
                .flatMap(repository::findById)
                .map(TableMapper.INSTANCE::toDTO)
                .orElse(null);
    }

    @Override
    public boolean deleteTable(Long id) {
        return  Optional.ofNullable(id)
                .map(i -> {
                    repository.deleteById(i);
                    return repository.findById(i).isEmpty();
                })
                .orElse(false);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TableDTO> findAll() {
        List<Table> result = repository.findAll();
        return TableMapper.INSTANCE.toDTOList(result);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TableDTO> findForPage(int pageNumber, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = SortManager.defineCurrentSortDir(sortDir, sortField);
        Specification<Table> spec = TableSpecification.search(keyword);
        int maxPageNumber = PageCounter.getMaxPageNumber(repository.count(spec), pageNumber, pageSize);

        Pageable pageRequest = PageRequest.of(maxPageNumber, pageSize, sort);
        Page<Table> temp = repository.findAll(spec, pageRequest);

        List<Table> temp2 = temp.getContent();
        List<TableDTO> tableDTOList = TableMapper.INSTANCE.toDTOList(temp.getContent());
        return new PageImpl<>(tableDTOList, temp.getPageable(), temp.getTotalElements());
    }

}
