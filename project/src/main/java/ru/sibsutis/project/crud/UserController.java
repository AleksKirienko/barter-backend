package ru.sibsutis.project.crud;

import org.springframework.web.bind.annotation.*;
import ru.sibsutis.project.AuthorizationRow;
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
    public User authorizationUser(@RequestBody AuthorizationRow row) {
        return service.authorization(row.getEmail(), row.getPassword());
    }

    @PostMapping("/")
    public String userInfo(@RequestParam Long userId) {
        return service.getUserInfo(userId);
    }
}
