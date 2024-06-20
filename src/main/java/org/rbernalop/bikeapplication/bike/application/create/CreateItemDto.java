package org.rbernalop.bikeapplication.bike.application.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemDto {
  private String id;
  private String model;
  private String type;
  private String description;
}
