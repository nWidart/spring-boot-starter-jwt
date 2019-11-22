package com.nwidart.springbootstarterjwt.service;


import com.nwidart.springbootstarterjwt.entity.User;
import java.util.Optional;

public interface UserService {

  Optional<User> findByName(String name);
}
