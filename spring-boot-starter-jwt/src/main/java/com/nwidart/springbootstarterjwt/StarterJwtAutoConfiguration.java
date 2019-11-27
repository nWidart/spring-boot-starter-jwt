package com.nwidart.springbootstarterjwt;

import com.nwidart.springbootstarterjwt.repository.UserRepository;
import com.nwidart.springbootstarterjwt.service.UserService;
import com.nwidart.springbootstarterjwt.service.impl.UserServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;

@AutoConfigurationPackage
public class StarterJwtAutoConfiguration {

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }
}
