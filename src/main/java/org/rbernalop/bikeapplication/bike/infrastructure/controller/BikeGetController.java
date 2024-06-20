package org.rbernalop.bikeapplication.bike.infrastructure.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.rbernalop.bikeapplication.bike.application.find.BikeFinder;
import org.rbernalop.bikeapplication.bike.application.find.FindBikeDto;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeCriteria;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BikeGetController {
  private final BikeFinder bikeFinder;

  @GetMapping("api/v1/bike")
  @Cacheable(value = "address_cache")
  public List<FindBikeDto> getBike(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String manufacturer,
      @RequestParam(required = false) String itemType,
      @RequestParam boolean sortByNameAscending) {
    BikeCriteria criteria = new BikeCriteria(name, manufacturer, itemType, sortByNameAscending);
    return bikeFinder.findWithCriteria(criteria);
  }
}
