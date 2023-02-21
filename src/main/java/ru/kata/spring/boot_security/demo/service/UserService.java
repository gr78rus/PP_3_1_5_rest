package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    List<Role> getAllRoles();

    User findUserById(long id);

    User findUserByName(String name);

    User findUserByEmail(String email);

    Role findRoleById(long id);

    Role findRoleByName(String name);

    void saveUser(User user);

    void deleteUser(long id);

    void createRole(Role role);
}