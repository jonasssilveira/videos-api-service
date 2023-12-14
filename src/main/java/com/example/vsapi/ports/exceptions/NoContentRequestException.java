package com.example.vsapi.ports.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentRequestException extends RuntimeException{

    public NoContentRequestException(String message) {
        super(message);
    }

}