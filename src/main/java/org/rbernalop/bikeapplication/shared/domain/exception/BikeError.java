package org.rbernalop.bikeapplication.shared.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum BikeError {
  BIKE_ALREADY_EXISTS("This bike already exists", HttpStatus.CONFLICT),
  BIKE_ID_IS_NULL_OR_EMPTY("Bike id cannot be empty", HttpStatus.BAD_REQUEST),
  BIKE_NAME_IS_NULL_OR_EMPTY("Bike name cannot be empty", HttpStatus.BAD_REQUEST),
  ITEM_ID_IS_NULL_OR_EMPTY("Item id cannot be empty", HttpStatus.BAD_REQUEST),
  ITEM_MODEL_IS_NULL_OR_EMPTY("Item model cannot be empty", HttpStatus.BAD_REQUEST),
  ITEM_TYPE_IS_NULL_OR_EMPTY("Item type cannot be empty", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus status;
}
