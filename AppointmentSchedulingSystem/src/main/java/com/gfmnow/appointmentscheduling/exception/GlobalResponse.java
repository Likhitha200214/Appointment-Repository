package com.gfmnow.appointmentscheduling.exception;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class GlobalResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public GlobalResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}

