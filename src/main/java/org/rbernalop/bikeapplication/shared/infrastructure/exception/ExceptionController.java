package org.rbernalop.bikeapplication.shared.infrastructure.exception;

import org.rbernalop.bikeapplication.shared.domain.exception.BikeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
  @ExceptionHandler(BikeException.class)
  public ResponseEntity<CustomError> handle(BikeException exception) {
    return ResponseEntity
        .status(exception.getStatus())
        .body(new CustomError(exception.getMessage()));
  }
}
