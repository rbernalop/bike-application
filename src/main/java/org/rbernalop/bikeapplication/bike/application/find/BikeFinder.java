package org.rbernalop.bikeapplication.bike.application.find;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.bikeapplication.bike.application.BikeMapper;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeCriteria;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BikeFinder {
  private final BikeRepository bikeRepository;
  private final BikeMapper bikeMapper;

  public List<FindBikeDto> findWithCriteria(BikeCriteria criteria) {
    log.info("Finding bike with criteria name {}, manufacturer {}, item type {}", criteria.name(), criteria.manufacturer(), criteria.itemType());

    return bikeRepository.findByCriteria(criteria).stream()
        .map(bikeMapper::toFindBikeDto)
        .toList();
  }
}
