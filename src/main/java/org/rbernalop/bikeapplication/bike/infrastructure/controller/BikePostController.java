package org.rbernalop.bikeapplication.bike.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.rbernalop.bikeapplication.bike.application.create.CreateBikeDto;
import org.rbernalop.bikeapplication.bike.application.create.BikeCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BikePostController {
  private final BikeCreator bikeCreator;

  @PostMapping("api/v1/bike")
  @ResponseStatus(HttpStatus.CREATED)
  public void postBike(@RequestBody CreateBikeDto createBikeDto) {
    bikeCreator.createBike(createBikeDto);
  }
}
