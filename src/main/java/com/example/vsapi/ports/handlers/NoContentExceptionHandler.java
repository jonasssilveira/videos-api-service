package com.example.vsapi.ports.handlers;

import com.example.vsapi.ports.exceptions.NotFoundRequestException;
import com.example.vsapi.ports.exceptions.NoContentRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NoContentExceptionHandler {
    @ExceptionHandler(NotFoundRequestException.class)
    ResponseEntity<NoContentRequestExceptionDetails> handlerNotFoundException(NotFoundRequestException notFoundRequestException){
        return new ResponseEntity<>(
                (NoContentRequestExceptionDetails) NoContentRequestExceptionDetails.builder()
                        .details(notFoundRequestException.getMessage())
                        .title("no content")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(notFoundRequestException.getClass().getName())
                        .build(),HttpStatus.NOT_FOUND);

    }

}
