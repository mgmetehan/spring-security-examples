package com.mgmetehan.springsecuritydemo.repository;

import java.util.Optional;

import com.mgmetehan.springsecuritydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}