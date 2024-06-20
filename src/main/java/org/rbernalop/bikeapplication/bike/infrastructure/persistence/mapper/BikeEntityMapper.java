package org.rbernalop.bikeapplication.bike.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.rbernalop.bikeapplication.bike.domain.Bike;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.entity.BikeEntity;

@Mapper(componentModel = "spring")
public interface BikeEntityMapper {
  BikeEntity toEntity(Bike bike);
  Bike toDomain(BikeEntity bikeEntity);
}
