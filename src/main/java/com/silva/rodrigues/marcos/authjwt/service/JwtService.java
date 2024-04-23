package com.silva.rodrigues.marcos.authjwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.silva.rodrigues.marcos.authjwt.dto.TokenDto;
import com.silva.rodrigues.marcos.authjwt.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

  @Value("${api.security.token.secret}")
  private String secret;

  public TokenDto generateToken(String username) {
    try {
      var algoritmo = Algorithm.HMAC256(secret);
      var expirationDate = generateExpirationDate();
      var token = JWT.create()
              .withIssuer("API authjwt")
              .withSubject(username)
              .withExpiresAt(expirationDate)
              .sign(algoritmo);

      return new TokenDto(token, "Bearer", expirationDate.toString());
    } catch (JWTCreationException exception){
      throw new RuntimeException("erro ao gerar token jwt", exception);
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

  public String getSubject(String tokenValue) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
              .withIssuer("API authjwt")
              .build()
              .verify(tokenValue)
              .getSubject();
    } catch (JWTVerificationException exception){
      throw new RuntimeException("Token JWT inválido ou expirado");
    }
  }
}
