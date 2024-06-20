package org.rbernalop.bikeapplication.bike.infrastructure.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.rbernalop.bikeapplication.bike.domain.Bike;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeCriteria;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeRepository;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.entity.BikeEntity;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.jpa.BikeSpecificationBuilder;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.jpa.JpaBikeRepository;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.mapper.BikeEntityMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresBikeRepository implements BikeRepository {
  private final JpaBikeRepository jpaBikeRepository;
  private final BikeEntityMapper bikeEntityMapper;

  @Override
  public void save(Bike bike) {
    BikeEntity bikeEntity = bikeEntityMapper.toEntity(bike);
    jpaBikeRepository.save(bikeEntity);
  }

  @Override
  public List<Bike> findByCriteria(BikeCriteria criteria) {
    Specification<BikeEntity> bikeEntitySpecification = BikeSpecificationBuilder.getSpecification(criteria);
    Sort sort = criteria.sortByNameAscending() ?
        Sort.by("name").ascending() : Sort.by("name").descending();

    List<BikeEntity> bikes = jpaBikeRepository.findAll(bikeEntitySpecification, sort);
    return bikes.stream()
        .map(bikeEntityMapper::toDomain)
        .toList();
  }

  @Override
  public boolean existsById(String id) {
    return jpaBikeRepository.existsById(id);
  }
}
