package br.com.unidrive.exceptions;

import io.jsonwebtoken.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityViolation(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(DataIntegrityViolationException.class + ": " + exception.getMessage());
    }


    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> signatureException(SignatureException exception) {
        return ResponseEntity.badRequest().body(SignatureException.class + ": " + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exptions(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}