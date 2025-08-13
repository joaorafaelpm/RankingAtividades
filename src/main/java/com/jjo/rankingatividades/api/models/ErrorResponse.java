package com.jjo.rankingatividades.api.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe generica de resposta para exceptions do ExceptionHandler
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{
    
    private String code ;
    private String message ;
    private LocalDateTime timestamp ;
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }


}
