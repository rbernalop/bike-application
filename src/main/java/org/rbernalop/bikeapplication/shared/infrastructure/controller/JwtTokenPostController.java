package org.rbernalop.bikeapplication.shared.infrastructure.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.rbernalop.bikeapplication.shared.infrastructure.configuration.JwtConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtTokenPostController {
  private final JwtConfiguration jwtConfiguration;

  @GetMapping("api/v1/token")
  public String generateJwtToken() {
    String userId = UUID.randomUUID().toString();
    long tokenExpirationTime = jwtConfiguration.getExpiration();
    Date createdDate = new Date();
    Date expirationDate = new Date(createdDate.getTime() + tokenExpirationTime);

    return JWT.create()
        .withSubject(userId)
        .withIssuedAt(createdDate)
        .withExpiresAt(expirationDate)
        .sign(Algorithm.HMAC512(jwtConfiguration.getKey()));
  }
}
