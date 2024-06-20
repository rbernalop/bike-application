package org.rbernalop.bikeapplication.bike.application;

import org.mapstruct.Mapper;
import org.rbernalop.bikeapplication.bike.application.create.CreateBikeDto;
import org.rbernalop.bikeapplication.bike.application.find.FindBikeDto;
import org.rbernalop.bikeapplication.bike.domain.Bike;

@Mapper(componentModel = "spring")
public interface BikeMapper {
  Bike toDomain(CreateBikeDto bikeDto);
  FindBikeDto toFindBikeDto(Bike bike);
}
