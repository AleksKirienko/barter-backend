package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import ru.sibsutis.project.databases.Product;
import ru.sibsutis.project.dto.ProductDto;
import ru.sibsutis.project.dto.ProductDtoWithId;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    private ProductDtoWithId copyToDto(Product product) {
        ProductDtoWithId productDto = new ProductDtoWithId();
        BeanUtils.copyProperties(product, productDto, "owner", "status", "userFaves", "productsForExchange");
        return productDto;
    }

    private List<ProductDtoWithId> copyToDto(List<Product> products) {
        return products.stream().map(this::copyToDto).collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<ProductDtoWithId> getAllProducts() {
        return copyToDto(service.getAll());
    }

    @PostMapping("/add")
    public ProductDtoWithId addNewProduct(@RequestBody ProductDto productDto, @RequestParam Long userID) {
        return copyToDto(service.create(productDto, userID));
    }

    @GetMapping("/del")
    public void deleteByID(@RequestParam Long productID) {
        service.delete(productID);
    }

    @PostMapping("/myproducts")
    public List<ProductDtoWithId> getProductsByUserId(@RequestParam Long userId) {
        return copyToDto(service.getById(userId));
    }

    @PostMapping("/faves")
    public List<ProductDtoWithId> getFavesByUserId(@RequestParam Long userId) {
        return copyToDto(service.getFavesById(userId));
    }

    @PostMapping("/fp")//todo
    public void addFromProfile(@RequestParam Long productId, @RequestBody List<Product> productsId) {
        service.addFromProfile(productId, productsId);
    }

    @PostMapping("/fh")//todo
    public void addFromHomeOrFaves(@RequestParam Long productId, @RequestBody List<Product> productsId) {
        service.addFromHome(productId, productsId);
    }

    @PostMapping("/product_info")
    public ProductDtoWithId productDtoInfo(@RequestParam Long productId) {
        return copyToDto(service.productInfo(productId));
    }

    @PostMapping("/exchanges")
    public List<ProductDtoWithId> getProductsForExchange(@RequestParam Long productId) {
        return copyToDto(service.getExchangesByProductId(productId));
    }

    @PostMapping("/delete_exchange")
    public void deleteExchange(@RequestParam Long availableId, @RequestParam Long exchangeId) {
        service.deleteExchange(availableId, exchangeId);
    }

}
