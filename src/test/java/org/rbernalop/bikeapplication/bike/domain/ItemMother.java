package org.rbernalop.bikeapplication.bike.domain;

import com.github.javafaker.Faker;
import java.util.UUID;
import org.rbernalop.bikeapplication.bike.domain.entity.Item;

public class ItemMother {
  public static Item random() {
    Faker faker = Faker.instance();
    return new Item(
        UUID.randomUUID().toString(),
        faker.pokemon().name(),
        faker.lorem().paragraph(),
        faker.backToTheFuture().character()
    );
  }
}