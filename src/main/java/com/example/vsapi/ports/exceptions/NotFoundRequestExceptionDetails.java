package com.example.vsapi.ports.exceptions;

import java.time.LocalDateTime;

public class NotFoundRequestExceptionDetails extends RequestException{

    NotFoundRequestExceptionDetails(LocalDateTime timestamp, Integer status, String title, String details, String message) {
        super(timestamp, status, title, details, message);
    }

}
