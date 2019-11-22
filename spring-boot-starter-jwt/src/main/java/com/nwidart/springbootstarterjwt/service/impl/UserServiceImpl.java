package com.nwidart.springbootstarterjwt.service.impl;


import com.nwidart.springbootstarterjwt.entity.User;
import com.nwidart.springbootstarterjwt.repository.UserRepository;
import com.nwidart.springbootstarterjwt.service.UserService;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findByName(String name) {
    Objects.requireNonNull(name, "name must be not null");
    return userRepository.findFirstByName(name);
  }

}
