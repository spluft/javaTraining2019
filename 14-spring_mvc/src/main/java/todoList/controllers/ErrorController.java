package todoList.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import todoList.service.exception.DataBaseException;
import todoList.service.exception.LoginFailure;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorController {

    private static String ERROR_PAGE = "error";

    @ExceptionHandler(LoginFailure.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
        return ERROR_PAGE;
    }

    @ExceptionHandler(DataBaseException.class)
    public String handleDataException(HttpServletRequest request, Exception ex){
        return ERROR_PAGE;
    }

}
