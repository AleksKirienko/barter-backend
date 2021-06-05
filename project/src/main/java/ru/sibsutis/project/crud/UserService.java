package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sibsutis.project.AuthorizationFault;
import ru.sibsutis.project.NotFoundException;
import ru.sibsutis.project.UserAlreadyExistException;
import ru.sibsutis.project.databases.Product;
import ru.sibsutis.project.databases.User;
import ru.sibsutis.project.dto.UserDto;

@Service
public class UserService {


    private final UserRepository repository;
    private final ProductRepository productRepository;

    public UserService(UserRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public User create(UserDto userDto) {
        User user = new User();
        boolean isExist = repository.existsUserByEmail(userDto.getEmail());
        if (isExist) {
            throw new UserAlreadyExistException();
        }

        BeanUtils.copyProperties(userDto, user, "password");
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setFavorites();
        return repository.save(user);
    }


    public User authorization(String email, String password) {
        User user = repository.getUserByEmail(email);
        if (user == null) {
            throw new AuthorizationFault();
        }

        boolean isCorrect = new BCryptPasswordEncoder().matches(password, user.getPassword());
        if (!isCorrect) {
            throw new AuthorizationFault();
        }
        return user;
    }

    public User getUserInfo(Long userId) {
        return repository.findById(userId).orElseThrow(NotFoundException::new);
    }

    public void addFaves(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);
        User user = repository.findById(userId).orElseThrow(NotFoundException::new);
        user.addFavorites(product);
        repository.save(user);
    }

    public void deleteFaves(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);
        User user = repository.findById(userId).orElseThrow(NotFoundException::new);
        user.deleteFavorites(product);
        repository.save(user);
    }
}
