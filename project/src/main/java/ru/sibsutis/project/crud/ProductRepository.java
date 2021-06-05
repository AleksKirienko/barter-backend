package ru.sibsutis.project.crud;

import ru.sibsutis.project.databases.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sibsutis.project.databases.User;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOwner(User user);

    List<Product> findByCategory(String category);

    List<Product> findByName(String name);

    List<Product> findAllByStatus(boolean status);
}
