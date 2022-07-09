package com.mgmetehan.springsecuritydemo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mgmetehan.springsecuritydemo.model.Role;
import com.mgmetehan.springsecuritydemo.model.User;
import com.mgmetehan.springsecuritydemo.repository.RoleRepository;
import com.mgmetehan.springsecuritydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DefaultUserService implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
    @Override
    public User get(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found.");
        }
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
