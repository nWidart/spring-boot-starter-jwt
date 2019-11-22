package com.nwidart.demo.controller;

import com.nwidart.springbootstarterjwt.dto.JwtResponse;
import com.nwidart.springbootstarterjwt.dto.UserDto;
import com.nwidart.springbootstarterjwt.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final TokenService tokenService;
  private AuthenticationManager authenticationManager;

  public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/authenticate")
  @CrossOrigin("*")
  public ResponseEntity<?> authenticate(@RequestBody UserDto userDto) throws Exception {
    authenticate(userDto.getUsername(), userDto.getPassword());

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String token = tokenService.generateToken(authentication);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      Authentication authResult = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, password));
      SecurityContextHolder.getContext().setAuthentication(authResult);
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
