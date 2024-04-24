package com.silva.rodrigues.marcos.authjwt.exception;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@RestControllerAdvice
public class HandlerErrorController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity badRequest(MethodArgumentNotValidException ex) {
    var map = buildErrorResponse(HttpStatus.BAD_REQUEST);
    var erros = ex
            .getFieldErrors()
            .stream()
            .map(FieldErrorDto::new)
            .toList();
    map.put("errors", erros);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
  }

  @ExceptionHandler({
          TaskNotFoundException.class,
          UsernameAlreadyExistsException.class,
          JwtException.class
  })
  public ResponseEntity genericBadRequest(RuntimeException ex) {
    var map = buildErrorMessageResponse(HttpStatus.BAD_REQUEST, ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
  }

  private HashMap<String, Object> buildErrorResponse(HttpStatus status) {
    var map = new HashMap<String, Object>();
    map.put("timestamp", LocalDateTime.now());
    map.put("status", status.value());
    return map;
  }

  private HashMap<String, Object> buildErrorMessageResponse(HttpStatus status, String message) {
    var map = buildErrorResponse(status);
    map.put("message", message);
    return map;
  }

  private record FieldErrorDto(String field, String message) {
    FieldErrorDto(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }

}
