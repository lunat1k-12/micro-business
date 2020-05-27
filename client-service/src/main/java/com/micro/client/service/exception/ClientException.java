package com.micro.client.service.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

    private final String code;

    public ClientException(String message) {
        super(message);
        this.code = null;
    }

    public ClientException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
        code = null;
    }
}
