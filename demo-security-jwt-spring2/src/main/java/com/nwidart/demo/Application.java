package com.nwidart.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan({"com.nwidart.demo.*", "com.nwidart.springbootstarterjwt.*"})
// @ComponentScan({"com.nwidart.demo", "com.nwidart.springbootstarterjwt"})
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
