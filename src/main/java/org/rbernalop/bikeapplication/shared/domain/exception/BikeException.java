package org.rbernalop.bikeapplication.shared.domain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class BikeException extends RuntimeException {
  private final BikeError error;

  @Override
  public String getMessage() {
    return error.getMessage();
  }

  public HttpStatus getStatus() {
    return error.getStatus();
  }
}
