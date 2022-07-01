package com.disney.demo.controller;

import com.disney.demo.dto.ApiErrorDto;
import com.disney.demo.exception.ParamNotFound;
import com.disney.demo.exception.UserAlreadyExistAuthenticationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    // aqui le digo el valor que debe capturar la exception
    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request){
        ApiErrorDto errorDto = new ApiErrorDto(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        Arrays.asList("Param Not Found"));
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request );
    }
    
    /**
     * esto es para capturar cuando los @Valid dan errores
     */
//    @Override
    protected ResponseEntity<Object> HandleMethodArgumentNotValid(
                                                MethodArgumentNotValidException ex, 
                                                HttpHeaders headers, 
                                                HttpStatus status, 
                                                WebRequest request){
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ":" + error.getDefaultMessage());            
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ":" + error.getDefaultMessage());            
        }
        ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
        
    }
    
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "this should be aplication specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    
    @ExceptionHandler(value = {UserAlreadyExistAuthenticationException.class})
    public ResponseEntity<Object> handleUserAlreadyExist(RuntimeException ex, WebRequest request){
        ApiErrorDto errorDto = new ApiErrorDto(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        Arrays.asList("User Already Exist"));
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request );
    
    }
}