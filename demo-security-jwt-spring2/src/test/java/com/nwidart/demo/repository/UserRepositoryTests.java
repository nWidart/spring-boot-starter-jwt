package com.nwidart.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nwidart.springbootstarterjwt.StarterJwtAutoConfiguration;
import com.nwidart.springbootstarterjwt.entity.User;
import com.nwidart.springbootstarterjwt.repository.UserRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(StarterJwtAutoConfiguration.class)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail() {
        User expected = testEntityManager.persistFlushFind(User.of("user_a_name", "user_a_pass", "aaa@example.com"));

        Optional<User> user = userRepository.findByEmail(expected.getEmail());
        User actual = user.orElseThrow(RuntimeException::new);
        assertThat(actual).isEqualTo(expected);
    }

}
