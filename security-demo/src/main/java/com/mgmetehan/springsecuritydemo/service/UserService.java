package com.mgmetehan.springsecuritydemo.service;

import com.mgmetehan.springsecuritydemo.model.Role;
import com.mgmetehan.springsecuritydemo.model.User;

import java.util.List;


public interface UserService {

    User save(User user);

    Role save(Role role);

    void addRoleToUser(String username, String roleName);

    User get(String username);

    List<User> list();

}
