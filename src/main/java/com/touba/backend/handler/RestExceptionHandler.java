package com.touba.backend.handler;

import com.example.authjwt.dto.ErrorDto;
import com.example.authjwt.dto.ValidationErrorDto;
import com.touba.backend.exception.EntityInvalidException;
import com.touba.backend.exception.EntityNotFoundException;
import com.touba.backend.exception.ErrorCode;
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
                .code(ErrorCode.AUTH_BAD_CREDENTIALS)
                .message(exception.getMessage())
                .errors(Collections.emptyList())
                .validationErrors(Collections.emptyList())
                .build();
        return new ResponseEntity<>(errorDto, badRequest);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(notFound.value())
                .code(exception.getCode())
                .message(exception.getMessage())
                .errors(Collections.emptyList())
                .validationErrors(Collections.emptyList())
                .build();
        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(EntityInvalidException.class)
    public ResponseEntity<ErrorDto> handleEntityInvalidException(EntityInvalidException exception) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(badRequest.value())
                .code(exception.getCode())
                .message(exception.getMessage())
                .errors(exception.getValidationErrors().stream().map(ValidationErrorDto::getCode).toList())
                .validationErrors(exception.getValidationErrors())
                .build();
        return new ResponseEntity<>(errorDto, badRequest);
    }

}
