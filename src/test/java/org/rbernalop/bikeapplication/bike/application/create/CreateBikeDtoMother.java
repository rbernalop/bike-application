package org.rbernalop.bikeapplication.bike.application.create;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.UUID;

public class CreateBikeDtoMother {
  public static CreateBikeDto random() {
    Faker faker = Faker.instance();
    CreateBikeDto dto = new CreateBikeDto();
    dto.setId(UUID.randomUUID().toString());
    dto.setName(faker.animal().name());
    dto.setDescription(faker.lorem().paragraph());
    dto.setPrice(faker.number().randomDouble(2, 0, 2000));
    dto.setManufacturer(faker.company().name());
    dto.setItems(List.of(CreateItemDtoMother.random()));
    return dto;
  }

  public static CreateBikeDto randomWithInvalidId() {
    CreateBikeDto dto = random();
    dto.setId(null);
    return dto;
  }

  public static CreateBikeDto randomWithInvalidName() {
    CreateBikeDto dto = random();
    dto.setName(null);
    return dto;
  }
}