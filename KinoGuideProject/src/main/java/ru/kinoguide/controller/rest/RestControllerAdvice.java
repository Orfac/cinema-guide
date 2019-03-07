package ru.kinoguide.controller.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(basePackages = {"ru.kinoguide.controller.rest"})
public class RestControllerAdvice {

//    @ExceptionHandler
//    public ResponseEntity handle(Exception ex) {
//        return new ResponseEntity<String>("Error has happened " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
