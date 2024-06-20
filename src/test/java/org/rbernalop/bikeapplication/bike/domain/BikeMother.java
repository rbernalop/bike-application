package org.rbernalop.bikeapplication.bike.domain;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.UUID;

public class BikeMother {

  public static Bike random() {
    Faker faker = Faker.instance();
    return new Bike(
        UUID.randomUUID().toString(),
        faker.animal().name(),
        faker.lorem().paragraph(),
        faker.number().randomDouble(2, 0, 2000),
        faker.company().name(),
        List.of(ItemMother.random())
    );
  }
}