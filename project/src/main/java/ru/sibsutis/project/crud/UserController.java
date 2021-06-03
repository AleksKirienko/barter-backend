package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
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

    private UserDto copyToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto, "id", "favorites");
        return userDto;
    }

    @PostMapping("/reg")
    public UserDto registrationUser(@RequestBody UserDto userDto) {
        return copyToDto(service.create(userDto));
    }

    @PostMapping("/auth")
    public UserDto authorizationUser(@RequestBody AuthorizationRow row) {
        return copyToDto(service.authorization(row.getEmail(), row.getPassword()));
    }

    @PostMapping("/info")
    public UserDto userInfo(@RequestParam Long userId) {
        return copyToDto(service.getUserInfo(userId));
    }

    @PostMapping("/add_faves")
    public User addFaves(@RequestParam Long productId, @RequestParam Long userId) {
        return service.addFaves(productId, userId);
    }
}
