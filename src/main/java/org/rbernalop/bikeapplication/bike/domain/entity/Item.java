package org.rbernalop.bikeapplication.bike.domain.entity;

import org.rbernalop.bikeapplication.shared.domain.FieldValidator;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeError;

public record Item(String id, String model, String type, String description) {
  public Item(String id, String model, String type, String description) {
    this.id = FieldValidator.requireNonNull(id, BikeError.ITEM_ID_IS_NULL_OR_EMPTY);
    this.model = FieldValidator.requireNonNull(model, BikeError.ITEM_MODEL_IS_NULL_OR_EMPTY);
    this.type = FieldValidator.requireNonNull(type, BikeError.ITEM_TYPE_IS_NULL_OR_EMPTY);
    this.description = description;
  }
}
