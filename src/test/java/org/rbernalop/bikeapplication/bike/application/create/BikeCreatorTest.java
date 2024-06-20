package org.rbernalop.bikeapplication.bike.application.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.rbernalop.bikeapplication.bike.application.BikeMapper;
import org.rbernalop.bikeapplication.bike.domain.Bike;
import org.rbernalop.bikeapplication.bike.domain.entity.Item;
import org.rbernalop.bikeapplication.bike.domain.repository.BikeRepository;
import org.rbernalop.bikeapplication.shared.application.UnitTestCase;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeError;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeException;

class BikeCreatorTest extends UnitTestCase {
  @Mock
  private BikeRepository bikeRepository;

  private BikeCreator bikeCreator;

  @BeforeEach
  void setUp() {
    bikeCreator = new BikeCreator(bikeRepository, Mappers.getMapper(BikeMapper.class));
  }

  @Test
  void shouldCreateBike() {
    // GIVEN
    ArgumentCaptor<Bike> bikeCaptor = ArgumentCaptor.forClass(Bike.class);
    CreateBikeDto bikeDto = CreateBikeDtoMother.random();

    when(bikeRepository.existsById(bikeDto.getId())).thenReturn(false);

    // WHEN
    assertDoesNotThrow(() -> bikeCreator.createBike(bikeDto));

    // THEN
    verify(bikeRepository, times(1)).existsById(bikeDto.getId());
    verify(bikeRepository, times(1)).save(bikeCaptor.capture());
    assertEquals(bikeDto, bikeCaptor.getValue());
  }

  @Test
  void shouldThrowBikeExceptionWhenBikeAlreadyExists() {
    // GIVEN
    CreateBikeDto bikeDto = CreateBikeDtoMother.random();

    when(bikeRepository.existsById(bikeDto.getId())).thenReturn(true);

    // WHEN
    BikeException bikeException = assertThrows(BikeException.class, () -> bikeCreator.createBike(bikeDto));

    // THEN
    verify(bikeRepository, times(1)).existsById(bikeDto.getId());
    verify(bikeRepository, never()).save(any());
    assertBikeException(BikeError.BIKE_ALREADY_EXISTS, bikeException);
  }

  @ParameterizedTest
  @MethodSource("nullCasesProvider")
  void shouldThrowBikeExceptionWhenARequiredParameterIsNull(CreateBikeDto invalidDto, BikeError expectedError) {
    // WHEN
    BikeException bikeException = assertThrows(BikeException.class, () -> bikeCreator.createBike(invalidDto));

    // THEN
    verify(bikeRepository, never()).existsById(any());
    verify(bikeRepository, never()).save(any());
    assertBikeException(expectedError, bikeException);
  }

  static Stream<Arguments> nullCasesProvider() {
    return Stream.of(
        Arguments.of(CreateBikeDtoMother.randomWithInvalidId(), BikeError.BIKE_ID_IS_NULL_OR_EMPTY),
        Arguments.of(CreateBikeDtoMother.randomWithInvalidName(), BikeError.BIKE_NAME_IS_NULL_OR_EMPTY),
        Arguments.of(CreateBikeDtoMother.randomWithInvalidItemId(), BikeError.ITEM_ID_IS_NULL_OR_EMPTY),
        Arguments.of(CreateBikeDtoMother.randomWithInvalidItemModel(), BikeError.ITEM_MODEL_IS_NULL_OR_EMPTY),
        Arguments.of(CreateBikeDtoMother.randomWithInvalidItemType(), BikeError.ITEM_TYPE_IS_NULL_OR_EMPTY)
    );
  }


  private void assertEquals(CreateBikeDto expected, Bike actual) {
    Assertions.assertEquals(expected.getId(), actual.id());
    Assertions.assertEquals(expected.getName(), actual.name());
    Assertions.assertEquals(expected.getDescription(), actual.description());
    Assertions.assertEquals(expected.getPrice(), actual.price());
    Assertions.assertEquals(expected.getManufacturer(), actual.manufacturer());
    assertListEquals(expected.getItems(), actual.items(), this::assertEquals);
  }

  private void assertEquals(CreateItemDto expected, Item actual) {
    Assertions.assertEquals(expected.getId(), actual.id());
    Assertions.assertEquals(expected.getModel(), actual.model());
    Assertions.assertEquals(expected.getDescription(), actual.description());
    Assertions.assertEquals(expected.getType(), actual.type());
  }
}