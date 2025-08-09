package com.jjo.rankingatividades.domain.exceptions;

public class EmailEmUsoException extends RuntimeException {
    public EmailEmUsoException(String message) {
        super(message);
    }
}
