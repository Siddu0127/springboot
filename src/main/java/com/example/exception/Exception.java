package com.example.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class Exception {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> res = new HashMap<>();


        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName= ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            String status= String.valueOf(HttpStatus.BAD_REQUEST);
            res.put(fieldName, message);
        });


        return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {

        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

  /*  @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValid(ConstraintViolationException ex) {
        Map<String, String> res = new HashMap<>();


        ex.getConstraintViolations().forEach((error) -> {
            String fieldName= ((FieldError) error).getField();
            String message = ((FieldError) error).getDefaultMessage();
            String status= String.valueOf(HttpStatus.BAD_REQUEST);
            res.put(fieldName, message);
        });




        return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);



    }

   */
}
