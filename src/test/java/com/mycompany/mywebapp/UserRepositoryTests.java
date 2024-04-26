package com.mycompany.mywebapp;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.mywebapp.user.User;
import com.mycompany.mywebapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("Gagan@123");
        user.setPassword("123456");
        user.setFirstName("Gagan");
        user.setLastName("Sharma");
        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public  void testListAll(){
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testupdate(){
        Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setPassword("2345");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("2345");

    }

    @Test
    public void testGet(){
        Integer userId = 5;
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 7;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
