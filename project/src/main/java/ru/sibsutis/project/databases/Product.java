package ru.sibsutis.project.databases;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;
    private String category;
    private String description;
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name ="user_id"))
    List<User> usersFaves;

    @OneToMany(mappedBy = "product")
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

    public List<Product> getProductsForExchange() {
        return productsForExchange;
    }

    public void setProductsForExchange(List<Product> productsForExchange) {
        this.productsForExchange = productsForExchange;
    }

    public void addToExchange(Product product) {
        productsForExchange.add(product);
    }

    public void addToExchange(List<Product> products) {
        productsForExchange.addAll(products);
    }
}
