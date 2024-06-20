package org.rbernalop.bikeapplication.bike.domain.entity;

import java.util.Objects;

public record Item(String id, String model, String type, String description) {
  public Item(String id, String model, String type, String description) {
    this.id = Objects.requireNonNull(id);
    this.model = Objects.requireNonNull(model);
    this.type = Objects.requireNonNull(type);
    this.description = description;
  }
}
