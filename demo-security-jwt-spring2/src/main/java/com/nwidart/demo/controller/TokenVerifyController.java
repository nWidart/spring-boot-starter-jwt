package com.nwidart.demo.controller;

import com.nwidart.springbootstarterjwt.dto.VerifyToken;
import com.nwidart.springbootstarterjwt.security.TokenService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenVerifyController {

  private final TokenService tokenService;

  public TokenVerifyController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @PostMapping("/verify")
  public TokenValidResponse verify(@RequestBody VerifyToken tokenRequest) {
    try {
      tokenService.verifyToken(tokenRequest.getToken());
    } catch (Exception e) {
      return TokenValidResponse.builder().isValid(false).message(e.getMessage()).build();
    }

    return TokenValidResponse.builder().isValid(true).build();
  }

  @Builder
  @Data
  private static class TokenValidResponse {

    private boolean isValid;
    private String message;
  }
}
