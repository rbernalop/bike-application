package org.rbernalop.bikeapplication.it.shared;

import io.cucumber.java.Before;
import java.io.IOException;
import java.util.UUID;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.entity.BikeEntity;
import org.rbernalop.bikeapplication.bike.infrastructure.persistence.jpa.JpaBikeRepository;
import org.rbernalop.bikeapplication.it.IntegrationTest;
import org.rbernalop.bikeapplication.shared.infrastructure.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SharedGivenStep extends IntegrationTest {
    @Autowired
    private JpaBikeRepository bikeRepository;

    @Before("@WithAuthenticatedUser")
    public void setupAuthenticatedUser() throws IOException {
        BikeEntity bike = getContentByFileNameAsObject("initial-data/bike.json", BikeEntity.class);
        bikeRepository.deleteAll();
        bikeRepository.save(bike);

      UserApp userApp = new UserApp(UUID.randomUUID().toString());
      Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userApp,
          null,
          null
      );
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
