package br.com.example.upafacil.ms_agendamento.infrastructure.exeptionhandler;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

import java.time.Instant;

@ControllerAdvice
public class ControllerExeptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> validateError(ValidationException ex, ServerWebExchange exchange) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(ex.getMessage());
        error.setMessage("Validation error");
        error.setPath(exchange.getRequest().getPath().value());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> nullPoiterExeption(NullPointerException ex, ServerWebExchange exchange) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus.value());
        error.setError(ex.getMessage());
        error.setMessage("Null pointer exception");
        error.setPath(exchange.getRequest().getPath().value());

        return ResponseEntity.status(httpStatus).body(error);
    }


}
