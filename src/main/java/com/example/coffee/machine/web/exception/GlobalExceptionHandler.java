package com.example.coffee.machine.web.exception;

import com.example.coffee.machine.service.exception.IllegalBalanceException;
import com.example.coffee.machine.service.exception.IllegalChronologyException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final String message = "Unable to parse request body. Check data types and allowed values.";
        ErrorResponse body = getErrorResponse(status, message);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        String message = errors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        ErrorResponse body = getErrorResponse(status, message);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(value = {NoSuchRequestException.class, IllegalBalanceException.class})
    protected final ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getMessage();
        ErrorResponse body = getErrorResponse(status, message);
        return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = {IllegalChronologyException.class, IllegalArgumentException.class})
    protected final ResponseEntity<Object> handleInternalError(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();
        ErrorResponse body = getErrorResponse(status, message);
        return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    private ErrorResponse getErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(status.value());
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setMessage(message);
        return errorResponse;
    }
}
