package tables.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static tables.utils.Constants.ERROR_PAGE;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public String handleAnyException(Exception e) {
        return ERROR_PAGE;
    }

}
