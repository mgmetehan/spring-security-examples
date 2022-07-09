package com.mgmetehan.springsecuritydemo.controller;


import com.mgmetehan.springsecuritydemo.model.Role;
import com.mgmetehan.springsecuritydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;

    @PostMapping
    public Role save(@RequestBody Role role) {
        return userService.save(role);
    }
}
