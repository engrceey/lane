package com.zurum.lanefinance.exceptions;


import com.zurum.lanefinance.dtos.response.ApiResponse;
import com.zurum.lanefinance.exceptions.exceptionDto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error("{}",exception.getMessage());
        ErrorResponse errorDetails = new ErrorResponse(exception.getMessage());

        ApiResponse<Object> response = ApiResponse.builder()
                .isSuccessful(false)
                .statusMessage("An error occurred, check message below")
                .data(errorDetails)
                .build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadable(final HttpMessageNotReadableException e,
                                                                               final HttpHeaders headers,
                                                                               final HttpStatus status,
                                                                               final WebRequest request) {
        e.printStackTrace();
        ErrorResponse errorDetails = new ErrorResponse(new Date(), "Bad JSON sent", request.getDescription(false));

        ApiResponse<Object> response = ApiResponse.builder()
                .isSuccessful(false)
                .statusMessage("An error occurred, check message below")
                .data(errorDetails)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));

        ApiResponse<Object> response = ApiResponse.builder()
                .isSuccessful(false)
                .statusMessage("An error occurred, check message below")
                .data(errorDetails)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({ResourceCreationException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiResponse<Object>> resourceConflictException(Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));

        ApiResponse<Object> response = ApiResponse.builder()
                .isSuccessful(false)
                .statusMessage("An error occurred, check message below")
                .data(errorDetails)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> globalExceptionHandler(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));

        ApiResponse<Object> response = ApiResponse.builder()
                .isSuccessful(false)
                .statusMessage("An error occurred, check message below")
                .data(errorDetails)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(final javax.validation.ConstraintViolationException exception) {
        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        final String errors =  violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(""));
        final ErrorResponse errorDetails = new ErrorResponse(new Date(), errors, "");

        ApiResponse<Object> response = ApiResponse.builder()
                .isSuccessful(false)
                .statusMessage("An error occurred, check message below")
                .data(errorDetails)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Object>> handleGlobalExceptions(MethodArgumentNotValidException ex,
                                                                         WebRequest request) {
        String[] errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
        ErrorResponse errorDetails = new ErrorResponse(new Date(), "Bad Request", errors);

        ApiResponse<Object> response = ApiResponse.builder()
                .isSuccessful(false)
                .statusMessage("An error occurred, check message below")
                .data(errorDetails)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}