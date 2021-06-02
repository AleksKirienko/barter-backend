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
            vertexes.add(new Vertex(product, false));
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
            dfsCycles(pair.getKey(), pair.getKey(), new ArrayList<>());
        }
    }

    public void dfsCycles(Vertex start, Vertex current, List<Vertex> cycle) {

        if (current.getProduct() == start.getProduct() && !cycle.isEmpty()) {
            allCycles.add(new ArrayList<>(cycle));
            return;
        }

        current.setColored(true);
        cycle.add(current);
        for (Vertex v : matrix.get(current)) {
            if (!v.isColored() || v == start) {
                dfsCycles(start, v, cycle);
            }
        }
        current.setColored(false);
        cycle.remove(cycle.size()-1);
    }

    public List<List<Vertex>> getAllCycles() {
        return allCycles;
    }

    public void search() {

    }

}
