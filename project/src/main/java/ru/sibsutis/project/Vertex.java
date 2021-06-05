package ru.sibsutis.project;

import ru.sibsutis.project.databases.Product;

public class Vertex {
    private Product product;
    private int height;
    private Vertex parent;
    private Color color;
    private Vertex cycleFrom;
    private int cycleSize;

    public Vertex(Product p, Color c) {
        product = p;
        color = c;
        cycleSize = 0;

        cycleFrom = null;
        parent = this;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vertex getCycleFrom() {
        return cycleFrom;
    }

    public void setCycleFrom(Vertex cycleFrom) {
        this.cycleFrom = cycleFrom;
    }

    public int getCycleSize() {
        return cycleSize;
    }

    public void setCycleSize(int cycleSize) {
        this.cycleSize = cycleSize;
    }
}
