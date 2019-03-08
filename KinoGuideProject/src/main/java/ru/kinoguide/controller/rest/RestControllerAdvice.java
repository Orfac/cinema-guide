package ru.kinoguide.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"ru.kinoguide.controller.rest"})
public class RestControllerAdvice {

    @ExceptionHandler
    public ResponseEntity handle(Exception ex) {
        return new ResponseEntity<String>("Error has happened " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
