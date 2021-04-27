package ru.sibsutis.project.crud;

import ru.sibsutis.project.databases.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
