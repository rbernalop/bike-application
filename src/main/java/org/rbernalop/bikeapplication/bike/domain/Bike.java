package org.rbernalop.bikeapplication.bike.domain;

import java.util.List;
import org.rbernalop.bikeapplication.bike.domain.entity.Item;
import org.rbernalop.bikeapplication.shared.domain.FieldValidator;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeError;

public record Bike(String id, String name, String description, Double price, String manufacturer, List<Item> items) {
  public Bike(String id, String name, String description, Double price, String manufacturer, List<Item> items) {
    this.id = FieldValidator.requireNonNull(id, BikeError.BIKE_ID_IS_NULL_OR_EMPTY);
    this.name = FieldValidator.requireNonNull(name, BikeError.BIKE_NAME_IS_NULL_OR_EMPTY);
    this.description = description;
    this.price = price;
    this.manufacturer = manufacturer;
    this.items = items;
  }
}
