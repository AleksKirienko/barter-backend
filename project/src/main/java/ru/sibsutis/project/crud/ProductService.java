package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.sibsutis.project.NotFoundException;
import ru.sibsutis.project.databases.Product;
import ru.sibsutis.project.databases.User;
import ru.sibsutis.project.dto.ProductDto;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product create(ProductDto productDto, Long userID) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        User owner = userRepository.findById(userID).orElseThrow(NotFoundException::new);
        product.setOwner(owner);
        product.setStatus(true);//В КОНЕЧНОЙ ВЕРСИИ БУДЕТ ИЗНАЧАЛЬНО false. На true меняется при поступлении товара в офис
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Product> getById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return repository.findByOwner(user);
    }

    public List<Product> getFavesById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return user.getFavorites();
    }

    public List<Product> getExchangesByProductId(Long productId) {
        return repository.findById(productId).orElseThrow(NotFoundException::new).getProductsForExchange();
    }

    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategory(category);
    }

    public void addFromProfile(Long productId, List<Long> productsId) {
        Product product = repository.findById(productId).orElseThrow(NotFoundException::new);
        for (Long id : productsId) {
            Product p = repository.findById(id).orElseThrow(NotFoundException::new);
            product.addToExchange(p);
        }
    }


    public void addFromHome(Long productId, List<Long> productsId) {
        Product product = repository.findById(productId).orElseThrow(NotFoundException::new);
        for (Long id : productsId) {
            Product p = repository.findById(id).orElseThrow(NotFoundException::new);
            p.addToExchange(product);
        }
    }

    public Product productInfo(Long productId) {
        return repository.findById(productId).orElseThrow(NotFoundException::new);
    }
}
