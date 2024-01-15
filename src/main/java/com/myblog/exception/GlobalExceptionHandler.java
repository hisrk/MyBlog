package com.myblog.exception;

import com.myblog.payload.ErrorDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
//because ResponseEntity sends response back to potman

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> handleResourcenotFoundException(ResourceNotFoundException e, WebRequest webRequest) {

        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(e.getMessage(), new Date(), webRequest.getDescription(true));


        return new ResponseEntity<>(errorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
