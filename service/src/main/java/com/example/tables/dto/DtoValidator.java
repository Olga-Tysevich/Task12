package com.example.tables.dto;

import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.util.stream.Collectors;

@UtilityClass
public class DtoValidator {
    public static final String TABLE = "table";
    public static final String ERROR_MESSAGE = "errorMessage";

    public static boolean isValidTable(Model model, TableDTO tableDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining());
            model.addAttribute(ERROR_MESSAGE, error);
            model.addAttribute(TABLE, tableDTO);
            return false;
        }
        return true;
    }

}
