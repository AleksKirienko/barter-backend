package ru.sibsutis.project.databases;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;
    private String category;
    private String description;
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name ="user_id"))
    List<User> usersFaves;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.usersFaves = usersFaves;
    }
}
