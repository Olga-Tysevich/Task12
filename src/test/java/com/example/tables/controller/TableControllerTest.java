package com.example.tables.controller;

import static com.example.tables.utils.Constants.*;
import static com.example.tables.utils.MockConstant.BRAND_FIELD;
import static com.example.tables.utils.MockConstant.FIRST_TABLE_ID;
import static com.example.tables.utils.MockUtils.createTable;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.tables.dto.TableDTO;
import com.example.tables.service.TableService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class TableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableService tableService;

    @Autowired
    private TableController tableController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tableController).build();
    }

    @Test
    void showCreateTablePage() throws Exception {
        mockMvc.perform(get("/tables/table-create"))
                .andExpect(status().isOk())
                .andExpect(view().name("table"));
    }

    @Test
    void createTableTest() throws Exception {
        TableDTO table = createTable();
        performSaveOrUpdate(table,"/tables/table-create");
    }

    @SneakyThrows
    @Test
    void showPageTest() {
        Page<TableDTO> pageForDisplay = mock(Page.class);

        when(tableService.findForPage(FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), StringUtils.EMPTY)).thenReturn(pageForDisplay);

        mockMvc.perform(get("/tables/page/{pageNum}", FIRST_PAGE)
                .param(SORT_FIELD, BRAND_FIELD)
                .param(SORT_DIR, Sort.Direction.ASC.name())
                .param(KEYWORD, StringUtils.EMPTY))
                .andExpect(status().isOk())
                .andExpect(view().name(TABLE_LIST))
                .andExpect(model().attribute(PAGE, pageForDisplay))
                .andExpect(model().attribute(SORT_FIELD, BRAND_FIELD))
                .andExpect(model().attribute(SORT_DIR, Sort.Direction.DESC.name()))
                .andExpect(model().attribute(KEYWORD, StringUtils.EMPTY));

        verify(tableService, times(1)).findForPage(FIRST_PAGE, BRAND_FIELD, Sort.Direction.ASC.name(), StringUtils.EMPTY);
    }

    @Test
    void showUpdateTableTest() throws Exception {
        TableDTO table = mock(TableDTO.class);

        when(tableService.findById(FIRST_TABLE_ID)).thenReturn(table);

        mockMvc.perform(get("/tables/table-update/{id}", FIRST_TABLE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name(TABLE))
                .andExpect(model().attribute(TABLE, table));

        verify(tableService, times(1)).findById(FIRST_TABLE_ID);
    }

    @Test
    void updateTable() throws Exception {
        TableDTO table = createTable();
        table.setId(FIRST_TABLE_ID);
        performSaveOrUpdate(table, "/tables/table-update");
    }


    @Test
    void deleteTable() throws Exception {
        mockMvc.perform(get("/tables/table-delete/{id}", FIRST_TABLE_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tables"));
    }

    private void performSaveOrUpdate(TableDTO table, String postUrl) throws Exception {

        when(tableService.createOrUpdateTable(table)).thenReturn(table.getId());

        MockHttpServletRequestBuilder request = post(postUrl)
                .flashAttr("tableDTO", table);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tables"));

        verify(tableService, times(1)).createOrUpdateTable(table);
    }

}