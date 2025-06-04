package com.example.Pet.exception;

import com.example.Pet.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
            String mess = ex.getMessage();
            ApiResponse apiResponse = new ApiResponse(mess, false);
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>>
        handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
            Map<String, String> resp = new HashMap<>() ;
            ex.getBindingResult().getAllErrors().forEach((objectError) -> {
                String fieldName = ((FieldError)objectError).getField();
                String message = objectError.getDefaultMessage();
                resp.put(fieldName, message);
            });
            return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);

        }
    }
