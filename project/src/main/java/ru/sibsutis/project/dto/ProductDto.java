package ru.sibsutis.project.dto;

public class ProductDto {
    private final String name;
    private final String category;
    private final String description;
    private final String image;

    public ProductDto(String name, String category, String description, String image) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
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
}
