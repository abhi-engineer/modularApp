package com.modular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private int status;
    private String errorCode;

    public ErrorResponse(String message, String path, int status, String errorCode) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.path = path;
        this.status = status;
        this.errorCode = errorCode;
    }
}
