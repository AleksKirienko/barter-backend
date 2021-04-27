package ru.sibsutis.project.crud;

import ru.sibsutis.project.NotFoundException;
import ru.sibsutis.project.databases.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product get(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id) {
        Product savedProduct = repository.findById(id).orElseThrow(NotFoundException::new);
        return repository.save(savedProduct);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
