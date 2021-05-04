package ru.sibsutis.project;

import ru.sibsutis.project.databases.Product;

import java.util.List;

public class SearchPath {

    private int[][] adjacencyMatrix;

    public void buildMatrix(List<Product> products) {
        adjacencyMatrix = new int[products.size()][products.size()];

        for (Product product: products) {
            List<Product> faves = product.getOwner().getFavorites();
            for (Product fave: faves) {
                int i = products.indexOf(fave);
                adjacencyMatrix[i][products.indexOf(product)] = 1;
            }
        }

        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (adjacencyMatrix[i][j] != 1 || adjacencyMatrix[j][i] != 1) {
                    adjacencyMatrix[i][j] = 0;
                    adjacencyMatrix[i][j] = 0;
                }
            }
        }
    }

    public void search() {

    }

    public void sentOffer() {
    }
}
