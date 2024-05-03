package com.base64.gamesback.common.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends  RuntimeException{
    public ServerErrorException(String message) {
        super(message);
    }
}
