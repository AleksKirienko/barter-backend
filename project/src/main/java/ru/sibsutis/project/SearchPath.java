package ru.sibsutis.project;

import ru.sibsutis.project.databases.Product;

import java.util.*;

public class SearchPath {

    private final List<Product> products;
    private final HashMap<Vertex, List<Vertex>> matrix;
    private final List<List<Vertex>> allCycles;


    public SearchPath(List<Product> products) {
        this.products = products;
        matrix = new HashMap<>();
        allCycles = new ArrayList<>();
    }


    public void buildMatrixHash() {
        List<Vertex> vertexes = new ArrayList<>();
        for (Product product : products) {
            if (product.getStatus())
                vertexes.add(new Vertex(product, Color.WHITE));
        }

        for (Vertex vertex : vertexes) {
            List<Product> exchanges = vertex.getProduct().getProductsForExchange();
            List<Vertex> adjacent = new ArrayList<>();
            for (Vertex vertex1 : vertexes) {
                if (exchanges.contains(vertex1.getProduct()))
                    adjacent.add(vertex1);
            }
            matrix.put(vertex, adjacent);
        }

        for (Map.Entry<Vertex, List<Vertex>> pair : matrix.entrySet()) {
            if (pair.getKey().getColor() != Color.BLACK) {
                dfs(pair.getKey(), 0);
            }
        }

        for (Map.Entry<Vertex, List<Vertex>> pair : matrix.entrySet()) {
            List<Vertex> cycle = new ArrayList<>();
            printCycle(pair.getKey(), cycle);
            allCycles.add(cycle);
        }
    }

    private void dfs(Vertex vertex, int h) {
        vertex.setColor(Color.GRAY);
        vertex.setHeight(h);
        for (Vertex v: matrix.get(vertex)) {
            if (v.getColor() == Color.WHITE) {
                v.setParent(vertex);
                dfs(v, h+1);
            } else if (v.getColor() == Color.GRAY) {
                int len = vertex.getHeight() - v.getHeight() + 1;
                if (v.getCycleSize() < len) {
                    v.setCycleSize(len);
                    v.setCycleFrom(vertex);
                }
            }
        }
        vertex.setColor(Color.BLACK);
    }

    private void printCycle(Vertex vertex, List<Vertex> cycle) {
        if (vertex.getCycleFrom() == null) return;
        Vertex v = vertex.getCycleFrom();
        cycle.add(vertex);
        while(vertex != v) {
            cycle.add(v);
            v = v.getParent();
        }
    }

    public List<List<Vertex>> getAllCycles() {
        return allCycles;
    }


}
