package com.nwidart.springbootstarterjwt.configuration.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt-starter")
@PropertySource(value = {
    "classpath:jwt-starter.properties",
    "classpath:jwt-starter-${spring.profiles.active}.properties"
}, ignoreResourceNotFound = true)
public class JwtStarterProperties {

  @Value("jwt-starter.secret")
  private String secret;
}
