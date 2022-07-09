package com.mgmetehan.springsecuritydemo;

import com.mgmetehan.springsecuritydemo.model.Role;
import com.mgmetehan.springsecuritydemo.model.User;
import com.mgmetehan.springsecuritydemo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
          userService.save(Role.builder().name("ROLE_USER").build());
          userService.save(Role.builder().name("ROLE_ADMIN").build());

          userService.save(User.builder().name("John").username("jdoe").password("12234").roles(new HashSet<>()).build());

          userService.addRoleToUser("jdoe", "ROLE_USER");
          userService.addRoleToUser("jdoe", "ROLE_ADMIN");
        };
    }
}
