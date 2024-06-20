package org.rbernalop.bikeapplication.bike.application.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.bikeapplication.bike.application.BikeMapper;
import org.rbernalop.bikeapplication.bike.domain.Bike;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeRepository;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeError;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BikeCreator {
  private final BikeRepository bikeRepository;
  private final BikeMapper bikeMapper;

  public void createBike(CreateBikeDto createBikeDto) {
    log.info("Creating Bike with id {}, name {}, description {}, price {}, manufacturer {}", createBikeDto.getId(),
        createBikeDto.getName(), createBikeDto.getDescription(), createBikeDto.getPrice(), createBikeDto.getManufacturer());

    Bike bike = bikeMapper.toDomain(createBikeDto);

    if (bikeRepository.existsById(bike.id())) {
      throw new BikeException(BikeError.BIKE_ALREADY_EXISTS);
    }

    bikeRepository.save(bike);
  }
}
