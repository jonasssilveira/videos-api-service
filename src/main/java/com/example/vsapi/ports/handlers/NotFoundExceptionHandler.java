package com.example.vsapi.ports.handlers;

import com.example.vsapi.ports.exceptions.NotFoundRequestException;
import com.example.vsapi.ports.exceptions.NotFoundRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(NotFoundRequestException.class)
    ResponseEntity<NotFoundRequestExceptionDetails> handlerNotFoundException(NotFoundRequestException notFoundRequestException){
        return new ResponseEntity<>(
                (NotFoundRequestExceptionDetails) NotFoundRequestExceptionDetails.builder()
                        .details(notFoundRequestException.getMessage())
                        .title("Not Found Exception")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(notFoundRequestException.getClass().getName())
                        .build(),HttpStatus.NOT_FOUND);

    }

}
