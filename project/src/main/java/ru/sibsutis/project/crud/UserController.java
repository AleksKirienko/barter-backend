package ru.sibsutis.project.crud;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sibsutis.project.databases.User;
import ru.sibsutis.project.dto.UserDto;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/r")
    public User registrationUser(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @PostMapping("/a")
    public User authorizationUser(@RequestBody String email, @RequestBody String password) {
        return service.authorization(email, password);
    }
}
