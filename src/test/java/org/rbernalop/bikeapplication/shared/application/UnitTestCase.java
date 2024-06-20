package org.rbernalop.bikeapplication.shared.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.BiConsumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeError;
import org.rbernalop.bikeapplication.shared.domain.exception.BikeException;

@ExtendWith(MockitoExtension.class)
public abstract class UnitTestCase {
  protected static void assertBikeException(BikeError bikeError, BikeException bikeException) {
    assertEquals(bikeError.getMessage(), bikeException.getMessage());
    assertEquals(bikeError.getStatus(), bikeException.getStatus());
  }

  protected static <T, U> void assertListEquals(List<T> expected, List<U> actual, BiConsumer<T, U> assertMethod) {
    Assertions.assertEquals(expected.size(), actual.size());
    for (int i = 0; i < expected.size(); i++) {
      assertMethod.accept(expected.get(i), actual.get(i));
    }
  }

}