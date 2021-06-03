package ru.sibsutis.project.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import ru.sibsutis.project.databases.Product;
import ru.sibsutis.project.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    private ProductDto copyToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto, "id", "owner", "status", "userFaves", "productsForExchange");
        return productDto;
    }

    private List<ProductDto> copyToDto(List<Product> products) {
        return products.stream().map(this::copyToDto).collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProducts() {
        return copyToDto(service.getAll());
    }

    @PostMapping("/add")
    public ProductDto addNewProduct(@RequestBody ProductDto productDto, @RequestParam Long userID) {
        return copyToDto(service.create(productDto, userID));
    }

    @GetMapping("/del")
    public void deleteByID(@RequestParam Long productID) {
        service.delete(productID);
    }

    @PostMapping("/myproducts")
    public List<ProductDto> getProductsByUserId(@RequestParam Long userId) {
        return copyToDto(service.getById(userId));
    }

    @PostMapping("/faves")
    public List<ProductDto> getFavesByUserId(@RequestParam Long userId) {
        return copyToDto(service.getFavesById(userId));
    }

    @PostMapping("/fp")//todo
    public void addFromProfile(@RequestParam Long productId, @RequestParam List<Long> productsId) {
        service.addFromProfile(productId, productsId);
    }

    @PostMapping("/fh")//todo
    public void addFromHomeOrFaves(@RequestParam Long productId, @RequestParam List<Long> productsId) {
        service.addFromHome(productId, productsId);
    }

    @PostMapping("/product_info")
    public ProductDto productDtoInfo(@RequestParam Long productId) {
        return copyToDto(service.productInfo(productId));
    }

    @PostMapping("/exchanges")
    public List<ProductDto> getProductsForExchange(@RequestParam Long productId) {
        return copyToDto(service.getExchangesByProductId(productId));
    }

}
