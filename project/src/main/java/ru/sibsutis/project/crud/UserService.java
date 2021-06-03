package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.sibsutis.project.AuthorizationFault;
import ru.sibsutis.project.NotFoundException;
import ru.sibsutis.project.UserAlreadyExistException;
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
        if (isExist) {
            throw new UserAlreadyExistException();
        }

        BeanUtils.copyProperties(userDto, user, "password");
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return repository.save(user);
    }

    public User update(Long id) {
        User savedUser = repository.findById(id).orElseThrow(NotFoundException::new);
        return repository.save(savedUser);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User authorization(String email, String password) {
        User user = repository.getUserByEmail(email);
        if (user == null) throw new AuthorizationFault();
        else {
            boolean isCorrect = new BCryptPasswordEncoder().matches(password, user.getPassword());
            if (isCorrect) return user;
            else throw new AuthorizationFault();
        }
    }

    public User getUserInfo(Long userId) {
        User user = repository.findById(userId).orElseThrow(NotFoundException::new);
        return user;
    }
}
