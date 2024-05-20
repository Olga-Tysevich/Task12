package com.example.tables.service;

import com.example.tables.entity.Table;
import com.example.tables.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import com.example.tables.dto.TableDTO;
import com.example.tables.mappers.TableMapper;
import com.example.tables.service.TableService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.tables.utils.MockConstant.*;
import static com.example.tables.utils.MockUtils.createTable;
import static com.example.tables.utils.MockUtils.createTables;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureDataJdbc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
class TableServiceImplTest {

    private TableRepository tableRepository;

    private TableService tableService;

    @AfterEach
    void clearDB() {
        tableRepository.deleteAll();
    }

    @Test
    public void createOrUpdateTableTest() {
        TableDTO table = createTable();
        Long id = tableService.createOrUpdateTable(table);

        Table forUpdate = tableRepository.findById(id).orElse(null);

        assert forUpdate != null;
        Assertions.assertEquals(table.getSize(), forUpdate.getSize());
        Assertions.assertEquals(table.getBrand(), forUpdate.getBrand());
        Assertions.assertEquals(table.getColor(), forUpdate.getColor());
        Assertions.assertEquals(table.getMaterial(), forUpdate.getMaterial());

        forUpdate.setSize(SIZE2);
        forUpdate.setBrand(BRAND2);

        tableService.createOrUpdateTable(TableMapper.INSTANCE.toDTO(forUpdate));
        TableDTO actual = tableService.findById(forUpdate.getId());

        Assertions.assertNotEquals(SIZE1, actual.getSize());
        Assertions.assertNotEquals(BRAND1, actual.getBrand());

    }

    @Test
    public void findByIdTest() {
        Table expected = tableRepository.save(TableMapper.INSTANCE.toEntity(createTable()));

        TableDTO actual = tableService.findById(expected.getId());

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getBrand(), actual.getBrand());
        Assertions.assertEquals(expected.getSize(), actual.getSize());
        Assertions.assertEquals(expected.getColor(), actual.getColor());
        Assertions.assertEquals(expected.getMaterial(), actual.getMaterial());
    }

    @Test
    public void deleteTest() {
        Table forDelete = tableRepository.save(TableMapper.INSTANCE.toEntity(createTable()));
        tableService.deleteTable(forDelete.getId());
        Assertions.assertThrows(EntityNotFoundException.class, () -> tableService.findById(forDelete.getId()));
    }

    @Test
    public void findAllTest() {
        tableRepository.save(TableMapper.INSTANCE.toEntity(createTable()));
        List<TableDTO> allTables = tableService.findAll();
        Assertions.assertNotEquals(allTables.size(), EMPTY_LIST);
    }

    @Test
    public void findForPageTest() {
        List<TableDTO> forSave = createTables(LIST_SIZE);
        TableDTO tableForSearchByBrand = createTable();
        tableForSearchByBrand.setBrand(BRAND2);
        tableService.createOrUpdateTable(tableForSearchByBrand);
        forSave.forEach(tableService::createOrUpdateTable);

        Page<TableDTO> firstPageWithoutSearch = tableService.findForPage(FIRST_PAGE, FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), null);
        Page<TableDTO> pageDataByBrand = tableService.findForPage(FIRST_PAGE, FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), BRAND2);

        Assertions.assertEquals(firstPageWithoutSearch.getContent().size(), PAGE_SIZE);
        Assertions.assertEquals(pageDataByBrand.getContent().size(), PAGE_BY_BRAND_SEARCH_SIZE);
    }

}