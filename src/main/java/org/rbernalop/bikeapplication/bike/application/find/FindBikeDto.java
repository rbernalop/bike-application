package org.rbernalop.bikeapplication.bike.application.find;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBikeDto {
  private String id;
  private String name;
  private String description;
  private Double price;
  private String manufacturer;
  private List<FindItemDto> items;
}
