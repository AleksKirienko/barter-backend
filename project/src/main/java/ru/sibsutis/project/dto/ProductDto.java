package ru.sibsutis.project.dto;

public class ProductDto {
    private String name;
    private String category;
    private String description;
    private String image;

    public ProductDto(String name, String category, String description, String image) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public ProductDto() {
    }


    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
