package com.example.tables.service;

import com.example.tables.dto.TableDTO;
import com.example.tables.utils.MockUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.tables.utils.MockConstant.*;
import static com.example.tables.utils.MockUtils.createTable1;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureDataJdbc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TableServiceImplTest {

    @Autowired
    private TableService tableService;


    @BeforeEach
    void addToBD() {
        MockUtils.createTables().forEach(tableService::createOrUpdateTable);
    }

    @Test
    public void createOrUpdateTableTest() {
        List<TableDTO> before = tableService.findAll();

        TableDTO table = createTable1();
        tableService.createOrUpdateTable(table);

        List<TableDTO> after = tableService.findAll();

        Assertions.assertNotEquals(before.size(), after.size());

        TableDTO createdTable = after.get(after.size() - 1);

        Assertions.assertEquals(table.getSize(), createdTable.getSize());
        Assertions.assertEquals(table.getBrand(), createdTable.getBrand());
        Assertions.assertEquals(table.getColor(), createdTable.getColor());
        Assertions.assertEquals(table.getMaterial(), createdTable.getMaterial());

        createdTable.setSize(SIZES.get(1));
        createdTable.setBrand(BRANDS.get(1));

        tableService.createOrUpdateTable(createdTable);

        Assertions.assertNotEquals(table.getSize(), createdTable.getSize());
        Assertions.assertNotEquals(table.getBrand(), createdTable.getBrand());

    }

    @Test
    public void findByIdTest() {
        TableDTO table = tableService.findById(TABLE_ID_FOR_SEARCH);

        Assertions.assertNotNull(table);
        Assertions.assertEquals(table.getId(), TABLE_ID_FOR_SEARCH);
    }

    @Test
    public void deleteTest() {
        tableService.deleteTable(FIRST_TABLE_ID);
        Assertions.assertThrows(EntityNotFoundException.class, () -> tableService.findById(FIRST_TABLE_ID));
    }

    @Test
    public void findAllTest() {
        List<TableDTO> allTables = tableService.findAll();
        Assertions.assertNotEquals(allTables.size(), EMPTY_LIST);
    }

    @Test
    public void findForPageTest() {
        Page<TableDTO> firstPageWithoutSearch = tableService.findForPage(FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), null);
        Page<TableDTO> pageDataByBrand = tableService.findForPage(FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), BRAND_FOR_SEARCH);

        Assertions.assertEquals(firstPageWithoutSearch.getContent().size(), FIRST_PAGE_SIZE);
        Assertions.assertEquals(pageDataByBrand.getContent().size(), PAGE_BY_BRAND_SEARCH_SIZE);
    }

}