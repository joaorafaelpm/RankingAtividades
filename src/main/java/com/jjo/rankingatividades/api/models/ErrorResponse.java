package com.jjo.rankingatividades.api.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo genérico de resposta para erros capturados pelo ExceptionHandler.
 *
 * Contém um código de erro, mensagem explicativa e timestamp.
 * Pode ser utilizado para padronizar respostas de erro em toda a API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
