package ru.sibsutis.project.crud;

import org.springframework.web.bind.annotation.*;
import ru.sibsutis.project.databases.Product;
import ru.sibsutis.project.dto.ProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return service.getAll();
    }

    @PostMapping("/add")
    public Product addNewProduct(@RequestBody ProductDto productDto, @RequestParam Long userID) {
        return service.create(productDto, userID);
    }

    @GetMapping("/del")
    public void deleteByID(@RequestParam Long productID) {
        service.delete(productID);
    }

    @PostMapping("/myproducts")
    public List<Product> getProductsByUserId(@RequestParam Long userId) {
        return service.getById(userId);
    }

}
