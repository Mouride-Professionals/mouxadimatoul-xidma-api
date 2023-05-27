package com.touba.backend.handler;

import com.example.authjwt.dto.ErrorDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(Collections.emptyList())
                .build();
        return new ResponseEntity<>(errorDto, badRequest);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        final HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(internalServerError.value())
                .message(exception.getMessage())
                .errors(Collections.emptyList())
                .build();
        return new ResponseEntity<>(errorDto, internalServerError);
    }

    @ExceptionHandler(EntityInvalidException.class)
    public ResponseEntity<ErrorDto> handleEntityInvalidException(EntityInvalidException exception) {
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(errorDto, notFound);
    }

}
