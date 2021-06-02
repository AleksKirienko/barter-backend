package ru.sibsutis.project;

import ru.sibsutis.project.databases.Product;

public class Vertex {
    private Product product;
    private boolean color;

    public Vertex(Product p, boolean c) {
        product = p;
        color = c;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isColored() {
        return color;
    }

    public void setColored(boolean color) {
        this.color = color;
    }
}
