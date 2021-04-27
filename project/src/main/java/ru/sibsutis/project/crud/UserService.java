package ru.sibsutis.project.crud;

import ru.sibsutis.project.NotFoundException;
import ru.sibsutis.project.databases.User;
import org.springframework.stereotype.Service;
import ru.sibsutis.project.dto.UserDto;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User create(UserDto userDto) {
        User user = new User();
        boolean isExist = repository.existsUserByEmail(userDto.getEmail());
        if (isExist) return null;//todo

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setInteractionPost(userDto.isInteractionPost());
        user.setPrivileged(false);
        user.setNumber(userDto.getNumber());
        return repository.save(user);
    }

    public User update(Long id) {
        User savedUser = repository.findById(id).orElseThrow(NotFoundException::new);
        return repository.save(savedUser);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
