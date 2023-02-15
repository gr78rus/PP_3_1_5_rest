package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Init {

    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        userService.createRole(admin);
        userService.createRole(user);

        userService.saveUser(new User(
                "admin",
                "adminov",
                "admin@admin.com",
                "admin",
                List.of(admin, user)));
        userService.saveUser(new User(
                "user",
                "userov",
                "user@user.com",
                "user",
                List.of(user)));
    }
}
