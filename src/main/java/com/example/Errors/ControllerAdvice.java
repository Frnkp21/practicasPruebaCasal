package com.example.Errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ControllerAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex, request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(ex, request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleNoResourceFoundException(NoResourceFoundException ex) {
        return new ErrorMessage(ex, "Resource not found");
    }
}
