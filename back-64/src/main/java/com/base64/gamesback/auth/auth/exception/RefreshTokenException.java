package com.base64.gamesback.auth.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando ocurre un error con el token de actualización.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException{

    /**
     * Constructor de RefreshTokenException.
     *
     * @param message Mensaje de error.
     */
    public RefreshTokenException(String message) {
        super(message);
    }
}
