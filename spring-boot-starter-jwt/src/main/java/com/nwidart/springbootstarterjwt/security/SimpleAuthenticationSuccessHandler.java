package com.nwidart.springbootstarterjwt.security;

import com.nwidart.springbootstarterjwt.security.TokenService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Processing when authentication is successful
 */
@Slf4j
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final TokenService tokenService;

  public SimpleAuthenticationSuccessHandler(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response,
      Authentication auth) {
    if (response.isCommitted()) {
      log.info("Response has already been committed.");
      return;
    }
    setToken(response, tokenService.generateToken(auth));
    response.setStatus(HttpStatus.OK.value());
    clearAuthenticationAttributes(request);
  }

  private void setToken(HttpServletResponse response, String token) {
    response.setHeader("Authorization", String.format("Bearer %s", token));
  }

  /**
   * Removes temporary authentication-related data which may have been stored in the session during the authentication
   * process.
   */
  private void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

}
