package org.rbernalop.bikeapplication.bike.application.find;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.rbernalop.bikeapplication.bike.application.BikeMapper;
import org.rbernalop.bikeapplication.bike.domain.Bike;
import org.rbernalop.bikeapplication.bike.domain.entity.Item;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeCriteria;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeRepository;
import org.rbernalop.bikeapplication.shared.application.UnitTestCase;
import org.rbernalop.bikeapplication.bike.domain.BikeMother;

class BikeFinderTest extends UnitTestCase {
  @Mock
  private BikeRepository bikeRepository;

  private BikeFinder bikeFinder;

  @BeforeEach
  void setUp() {
    bikeFinder = new BikeFinder(bikeRepository, Mappers.getMapper(BikeMapper.class));
  }

  @Test
  void shouldFindBikesByCriteria() {
    // GIVEN
    Bike expectedBike = BikeMother.random();
    List<Bike> expectedBikes = List.of(expectedBike);
    BikeCriteria bikeCriteria = new BikeCriteria(expectedBike.name(), expectedBike.manufacturer(), null, false);

    when(bikeRepository.findByCriteria(bikeCriteria)).thenReturn(expectedBikes);

    // WHEN
    List<FindBikeDto> actualBikes = assertDoesNotThrow(() -> bikeFinder.findWithCriteria(bikeCriteria));

    // THEN
    verify(bikeRepository, times(1)).findByCriteria(bikeCriteria);
    assertListEquals(expectedBikes, actualBikes, this::assertEquals);
  }


  private void assertEquals(Bike expected, FindBikeDto actual) {
    Assertions.assertEquals(expected.id(), actual.getId());
    Assertions.assertEquals(expected.name(), actual.getName());
    Assertions.assertEquals(expected.description(), actual.getDescription());
    Assertions.assertEquals(expected.price(), actual.getPrice());
    Assertions.assertEquals(expected.manufacturer(), actual.getManufacturer());
    assertListEquals(expected.items(), actual.getItems(), this::assertEquals);
  }

  private void assertEquals(Item expected, FindItemDto actual) {
    Assertions.assertEquals(expected.id(), actual.getId());
    Assertions.assertEquals(expected.model(), actual.getModel());
    Assertions.assertEquals(expected.description(), actual.getDescription());
    Assertions.assertEquals(expected.type(), actual.getType());
  }
}