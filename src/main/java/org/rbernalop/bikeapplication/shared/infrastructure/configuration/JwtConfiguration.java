package org.rbernalop.bikeapplication.shared.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfiguration {
    private String key;
    private long expiration;
}
