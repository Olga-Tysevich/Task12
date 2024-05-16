package com.example.tables.controller;

import com.example.tables.dto.TableDTO;
import com.example.tables.service.TableService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.stream.Collectors;

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
    public String createTable(Model model, @Valid TableDTO tableDTO, BindingResult bindingResult) {
        if (!isValidTable(model, tableDTO, bindingResult)) {
            return TABLE;
        }
        tableService.createOrUpdateTable(tableDTO);
        return TABLES_PAGE_REDIRECT;
    }

    @GetMapping("/table-update/{id}")
    public String showUpdateTable(Model model, @PathVariable(TABLE_ID) Long id) {
        TableDTO table = tableService.findById(id);
        model.addAttribute(TABLE, table);
        return TABLE;
    }

    @PostMapping("/table-update")
    public String updateTable(Model model, @Valid TableDTO tableDTO, BindingResult bindingResult) {
        if (!isValidTable(model, tableDTO, bindingResult)) {
            return TABLE;
        }
        tableService.createOrUpdateTable(tableDTO);
        return TABLES_PAGE_REDIRECT;
    }


    @GetMapping("/table-delete/{id}")
    public String deleteTable(@PathVariable(TABLE_ID) Long id) {
        tableService.deleteTable(id);
        return TABLES_PAGE_REDIRECT;
    }

    private boolean isValidTable(Model model, TableDTO tableDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining());
            model.addAttribute(ERROR, error);
            model.addAttribute(TABLE, tableDTO);
            return false;
        }
        return true;
    }

}
