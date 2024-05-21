package com.example.tables.controller;

import com.example.tables.dto.DtoValidator;
import com.example.tables.dto.TableDTO;
import com.example.tables.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.tables.service.SortManager;

import javax.validation.Valid;

import static com.example.tables.utils.Constants.*;


@Controller
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping
    public String showTablePage(Model model) {
        return showPage(model, FIRST_PAGE, BRAND, Sort.Direction.ASC.name(), null);
    }

    @GetMapping("/page/{pageNum}")
    public String showPage(Model model,
                           @PathVariable(PAGE_NUM) int pageNum,
                           @RequestParam(SORT_FIELD) String sortField,
                           @RequestParam(SORT_DIR) String sortDir,
                           @RequestParam(KEYWORD) String keyword) {
        Page<TableDTO> pageForDisplay = tableService.findForPage(pageNum, PAGE_SIZE, sortField, sortDir, keyword);
        model.addAttribute(PAGE, pageForDisplay);
        model.addAttribute(SORT_FIELD, sortField);
        model.addAttribute(SORT_DIR, SortManager.getNextSortDir(sortDir));
        model.addAttribute(KEYWORD, keyword);
        return TABLE_LIST;
    }

    @GetMapping("/table-create")
    public String showCreateTablePage() {
        return TABLE;
    }

    @PostMapping("/table-create")
    public String createTable(Model model, @Valid TableDTO tableDTO, BindingResult bindingResult) {
        if (!DtoValidator.isValidTable(model, tableDTO, bindingResult)) {
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
        if (!DtoValidator.isValidTable(model, tableDTO, bindingResult)) {
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

}
