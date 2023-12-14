package com.example.vsapi.ports.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestException {
    private LocalDateTime timestamp;
    private Integer status;
    private String title;
    private String details;
    private String message;
}