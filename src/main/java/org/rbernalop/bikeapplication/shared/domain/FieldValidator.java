package org.rbernalop.bikeapplication.shared.domain;

import org.rbernalop.bikeapplication.shared.domain.exception.BikeError;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeException;

public class FieldValidator {
  private FieldValidator() {}

  public static String requireNonNull(String value, BikeError error) {
    if (value == null || value.trim().isEmpty()) {
      throw new BikeException(error);
    }
    return value;
  }
}
