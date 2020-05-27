package com.micro.client.service.controller;

import com.micro.client.service.dto.ErrorMessage;
import com.micro.client.service.dto.ErrorResponse;
import com.micro.client.service.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_MSG = "Server error: %s";

    @ExceptionHandler(ClientException.class)
    protected ResponseEntity<ErrorResponse> handleClientException(ClientException ex) {
        final var response = ErrorResponse.builder()
                .clientError(ErrorMessage.builder()
                        .message(ex.getMessage())
                        .code(ex.getCode())
                        .build())
                .build();
        log.info("ClientException: {}", response, ex);
        MDC.clear();
        return new ResponseEntity<>(response, BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        final var errorId = UUID.randomUUID().toString();
        final var response = ErrorResponse.builder()
                .serverError(new ErrorMessage(String.format(INTERNAL_SERVER_ERROR_MSG, errorId)))
                .build();
        log.error(String.format("Unexpected error %s %s", errorId, ex.getMessage()), ex);
        MDC.clear();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
