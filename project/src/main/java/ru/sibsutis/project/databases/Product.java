package ru.sibsutis.project.databases;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;
    private String category;
    private String description;
    private String image;
    private boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name ="user_id"))
    List<User> usersFaves;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_product", joinColumns = @JoinColumn(name = "available_product_id"), inverseJoinColumns = @JoinColumn(name ="exchange_product_id"))
    List<Product> productsForExchange;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<User> getUsersFaves() {
        return usersFaves;
    }

    public void setUsersFaves(List<User> usersFaves) {
        this.usersFaves = Objects.requireNonNullElseGet(usersFaves, ArrayList::new);;
    }

    public void addUserFaves(User user) {
        getUsersFaves().add(user);
    }

    public List<Product> getProductsForExchange() {
        return productsForExchange;
    }

    public void setProductsForExchange(List<Product> productsForExchange) {
        this.productsForExchange = productsForExchange;
    }

    public void addToExchange(Product product) {
        productsForExchange.add(product);
    }

    public void deleteFromExchange(Product product) {
        productsForExchange.remove(product);
    }

    public void addToExchange(List<Product> products) {
        productsForExchange.addAll(products);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
