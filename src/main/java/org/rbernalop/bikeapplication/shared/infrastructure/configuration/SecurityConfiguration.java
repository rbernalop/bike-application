package org.rbernalop.bikeapplication.shared.infrastructure.configuration;

import lombok.AllArgsConstructor;
import org.rbernalop.bikeapplication.shared.infrastructure.filter.JwtTokenVerifierFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfiguration {

  private final JwtConfiguration jwtConfiguration;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .sessionManagement(
            httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .csrf(AbstractHttpConfigurer::disable)
        .addFilterAfter(jwtTokenVerifierFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry
                .requestMatchers(HttpMethod.POST, "api/v1/bike").authenticated()
                .requestMatchers(HttpMethod.GET, "api/v1/bike").authenticated()
                .anyRequest().permitAll()
        )
        .authenticationManager(authenticationManager(http));

    return http.build();
  }


  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http
        .getSharedObject(AuthenticationManagerBuilder.class);

    return authenticationManagerBuilder.build();
  }

  @Bean
  public JwtTokenVerifierFilter jwtTokenVerifierFilter() {
    return new JwtTokenVerifierFilter(this.jwtConfiguration.getKey());
  }

}
