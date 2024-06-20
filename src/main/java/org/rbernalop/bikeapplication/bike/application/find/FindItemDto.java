package org.rbernalop.bikeapplication.bike.application.find;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindItemDto {
  private String id;
  private String model;
  private String type;
  private String description;
}
