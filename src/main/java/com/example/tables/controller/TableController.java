package com.example.tables.controller;

import com.example.tables.dto.TableDTO;
import com.example.tables.service.TableService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static com.example.tables.utils.Constants.*;

@Controller
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public String showTablePage(Model model) {
        return showPage(model, FIRST_PAGE, BRAND, ASC_SORT, null);
    }

    @GetMapping("/page/{pageNum}")
    public String showPage(Model model,
                           @PathVariable(PAGE_NUM) int pageNum,
                           @RequestParam(SORT_FIELD) String sortField,
                           @RequestParam(SORT_DIR) String sortDir,
                           @RequestParam(KEYWORD) String keyword) {
        Page<TableDTO> pageForDisplay = tableService.findForPage(pageNum, sortField, sortDir, keyword);
        model.addAttribute(PAGE, pageForDisplay);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, sortDir);
        model.addAttribute(KEYWORD, keyword);
        return TABLE_LIST;
    }

    @GetMapping("/table-create")
    public String showCreateTablePage() {
        return TABLE;
    }

    @PostMapping("/table-create")
    public String createTable(TableDTO tableDTO) {
        tableService.createOrUpdateTable(tableDTO);
        return TABLES_PAGE_REDIRECT;
    }

    @GetMapping("/table-update/{id}")
    public String showUpdateTable(Model model,
                                  @PathVariable(TABLE_ID) Long id) {
        TableDTO table = tableService.findById(id);
        model.addAttribute(TABLE, table);
        return TABLE;
    }

    @PostMapping("/table-update")
    public String updateTable(TableDTO tableDTO) {
        tableService.createOrUpdateTable(tableDTO);
        return TABLES_PAGE_REDIRECT;
    }


    @GetMapping("/table-delete/{id}")
    public String deleteTable(@PathVariable(TABLE_ID) Long id) {
        tableService.deleteTable(id);
        return TABLES_PAGE_REDIRECT;
    }

}
