package com.example.tables.service;

import com.example.tables.dto.TableDTO;
import com.example.tables.utils.MockUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.tables.utils.MockConstant.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureDataJdbc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class TableServiceImplTest {

    @Autowired
    private TableService tableService;

    @Test
    public void createOrUpdateTableTest() {
        TableDTO table = MockUtils.createTable();
        tableService.createOrUpdateTable(table);

        TableDTO createResult = tableService.findById(ID_OF_CREATED_TABLE);

        Assertions.assertEquals(table.getSize(), createResult.getSize());
        Assertions.assertEquals(table.getBrand(), createResult.getBrand());
        Assertions.assertEquals(table.getColor(), createResult.getColor());
        Assertions.assertEquals(table.getMaterial(), createResult.getMaterial());

        table.setId(ID_OF_CREATED_TABLE);
        table.setSize(SIZES.get(1));
        table.setBrand(BRANDS.get(1));

        tableService.createOrUpdateTable(table);

        Assertions.assertNotEquals(table.getSize(), createResult.getSize());
        Assertions.assertNotEquals(table.getBrand(), createResult.getBrand());

    }

    @Test
    public void findByIdTest() {
        TableDTO table = tableService.findById(TABLE_ID_FOR_SEARCH);

        Assertions.assertNotNull(table);
        Assertions.assertEquals(table.getId(), TABLE_ID_FOR_SEARCH);
        Assertions.assertEquals(table.getBrand(), BRAND_FOR_SEARCH);
    }

    @Test
    public void deleteTest() {
        tableService.deleteTable(FIRST_TABLE_ID);
        Assertions.assertThrows(EntityNotFoundException.class, () -> tableService.findById(FIRST_TABLE_ID));
    }

    @Test
    public void findAllTest() {
        List<TableDTO> allTables = tableService.findAll();
        Assertions.assertEquals(allTables.size(), INITIAL_NUMBER_OF_ENTRIES);
    }

    @Test
    public void findForPageTest() {
        Page<TableDTO> firstPageWithoutSearch = tableService.findForPage(FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), null);
        Page<TableDTO> secondPageWithoutSearch = tableService.findForPage(SECOND_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), null);
        Page<TableDTO> pageDataByBrand = tableService.findForPage(FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), BRAND_FOR_SEARCH);

        Assertions.assertEquals(firstPageWithoutSearch.getContent().size(), FIRST_PAGE_SIZE);
        Assertions.assertEquals(secondPageWithoutSearch.getContent().size(), SECOND_PAGE_SIZE);
        Assertions.assertEquals(pageDataByBrand.getContent().size(), PAGE_BY_BRAND_SEARCH_SIZE);
    }

}