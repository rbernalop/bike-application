package org.rbernalop.bikeapplication.shared.infrastructure.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.bikeapplication.shared.infrastructure.UserApp;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenVerifierFilter extends OncePerRequestFilter {

  private final String secretJwtKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");
    if(authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authorizationHeader.replace("Bearer ", "");
    try {
      Algorithm algorithm = Algorithm.HMAC512(secretJwtKey);
      DecodedJWT decodedJwt = JWT.require(algorithm).build().verify(token);

      String id = decodedJwt.getSubject();

      UserApp userApp = new UserApp(id);

      Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userApp,
          null,
          null
      );

      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      handleRequest(request, response, filterChain);
    } catch (TokenExpiredException e) {
      log.error("Jwt token expired");
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Session expired.");
    } catch (Exception e) {
      log.error("Token {} cannot be trusted", token);
      response.sendError(HttpStatus.BAD_REQUEST.value(), "Try log in again.");
    }
  }

  private void handleRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    filterChain.doFilter(request, response);
  }
}