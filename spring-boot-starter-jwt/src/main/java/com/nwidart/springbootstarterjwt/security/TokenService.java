package com.nwidart.springbootstarterjwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

@Slf4j
public class TokenService {

  private static final Long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(10L);
  final private Algorithm algorithm;

  public TokenService(String secretKey) {
    Objects.requireNonNull(secretKey, "secret key must be not null");
    this.algorithm = Algorithm.HMAC512(secretKey);
  }

  public String generateToken(Authentication auth) {
    SimpleLoginUser loginUser = (SimpleLoginUser) auth.getPrincipal();
    Date issuedAt = new Date();
    Date notBefore = new Date(issuedAt.getTime());
    Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
    String token = JWT.create()
        .withIssuedAt(issuedAt)
        .withNotBefore(notBefore)
        .withExpiresAt(expiresAt)
        .withSubject(loginUser.getUser().getId().toString())
        .sign(this.algorithm);
    log.debug("generate token : {}", token);
    return token;
  }

  public DecodedJWT verifyToken(String token) {
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }
}
