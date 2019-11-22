package com.nwidart.springbootstarterjwt.repository;


import com.nwidart.springbootstarterjwt.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findFirstByName(String name);
}
