package org.rbernalop.bikeapplication.it.bike;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import org.rbernalop.bikeapplication.bike.application.create.CreateBikeDto;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.jpa.JpaBikeRepository;
import org.rbernalop.bikeapplication.it.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BikeThenSteps extends IntegrationTest {

  @Autowired
  private JpaBikeRepository bikeRepository;

  @And("bike exists in database")
  public void bikeExistsInDatabase() throws JsonProcessingException {
    CreateBikeDto request = context.getRequestBodyAsObject(CreateBikeDto.class);
    assertTrue(bikeRepository.existsById(request.getId()));
  }
}
