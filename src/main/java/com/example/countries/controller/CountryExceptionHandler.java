package com.example.countries.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.countries.controller.ErrorCode.*;

@ControllerAdvice
public class CountryExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException() {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Invalid params provided.", INVALID_ARGUMENTS);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCountryNotFoundException() {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Country not found.", COUNTRY_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(NoBordersException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoBordersException() {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Country has no borders.", NO_BORDERS);
        return ResponseEntity.status(HttpStatus.OK).body(errorResponseDTO);
    }
}
