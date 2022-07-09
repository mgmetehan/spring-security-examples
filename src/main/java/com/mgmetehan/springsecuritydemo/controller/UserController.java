package com.mgmetehan.springsecuritydemo.controller;

import java.util.List;

import com.mgmetehan.springsecuritydemo.model.User;
import com.mgmetehan.springsecuritydemo.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<User> list() {
        return userService.list();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PatchMapping("/roles")
    public void addRoles(@RequestBody AddRolesRequest request) {
        userService.addRoleToUser(request.getUsername(), request.getRole());
    }

    @Data
    class AddRolesRequest {
        private String username;
        private String role;
    }
}
