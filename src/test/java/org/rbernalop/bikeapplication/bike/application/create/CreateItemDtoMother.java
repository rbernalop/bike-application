package org.rbernalop.bikeapplication.bike.application.create;

import com.github.javafaker.Faker;
import java.util.UUID;

public class CreateItemDtoMother {
  public static CreateItemDto random() {
    Faker faker = Faker.instance();
    CreateItemDto dto = new CreateItemDto();
    dto.setId(UUID.randomUUID().toString());
    dto.setModel(faker.pokemon().name());
    dto.setDescription(faker.lorem().paragraph());
    dto.setType(faker.backToTheFuture().character());
    return dto;
  }
}