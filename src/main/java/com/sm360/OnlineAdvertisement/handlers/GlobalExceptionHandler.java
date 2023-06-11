package com.sm360.OnlineAdvertisement.handlers;

import com.sm360.OnlineAdvertisement.exceptions.ObjectValidationException;
import com.sm360.OnlineAdvertisement.exceptions.TierLimitReachedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
        var response = ExceptionRepresentation.builder()
                .errorMessage("Validation error")
                .errorSource(exception.getSource())
                .validationErrors(exception.getErrorMessages())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
        var response = ExceptionRepresentation.builder()
                .errorMessage("No record found")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TierLimitReachedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(TierLimitReachedException exception) {
        var response = ExceptionRepresentation.builder()
                .errorMessage("Tier limit reached")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
