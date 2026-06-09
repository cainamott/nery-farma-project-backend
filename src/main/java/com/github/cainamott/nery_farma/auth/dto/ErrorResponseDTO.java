package com.github.cainamott.nery_farma.auth.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(

        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {

    public static ErrorResponseDTO of(int status, String error, String message) {
      return new ErrorResponseDTO(status, error, message, LocalDateTime.now());
    };


}
