package ru.sibsutis.project.crud;

import org.springframework.stereotype.Service;
import ru.sibsutis.project.SearchPath;
import ru.sibsutis.project.Trade;
import ru.sibsutis.project.Vertex;
import ru.sibsutis.project.databases.Product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    private final ProductRepository productRepository;
    private final TradeRepository repository;

    public TradeService(ProductRepository productRepository, TradeRepository repository) {
        this.productRepository = productRepository;
        this.repository = repository;
    }

    private List<List<Product>> getPaths() {
        List<List<Product>> listCycles = new ArrayList<>();


        SearchPath s = new SearchPath(productRepository.findAll());
        s.buildMatrixHash();

        List<List<Vertex>> allCycles = s.getAllCycles();

        List<Vertex> alreadyInclude = new ArrayList<>();
        List<List<Vertex>> finalCycles = new ArrayList<>();

        while (chooseCycle(allCycles, alreadyInclude, finalCycles)) {
            // Do nothing
        }

        for (List<Vertex> cycles : finalCycles) {
            List<Product> products = new ArrayList<>();
            for (Vertex vertex : cycles) {
                products.add(vertex.getProduct());
            }
            listCycles.add(products);
        }
        return listCycles;
    }

    private static boolean chooseCycle(List<List<Vertex>> allCycles, List<Vertex> alreadyInclude, List<List<Vertex>> finalCycles) {
        int max = 0;
        int imax = 0;
        for (List<Vertex> cycle : allCycles) {
            if (cycle.size() > max && !intersects(cycle, alreadyInclude)) {
                max = cycle.size();
                imax = allCycles.indexOf(cycle);
            }
        }

        if (max == 0) {
            return false;
        }
        alreadyInclude.addAll(allCycles.get(imax));
        finalCycles.add(allCycles.get(imax));
        allCycles.remove(allCycles.get(imax));
        return true;

    }

    private static boolean intersects(List<Vertex> a, List<Vertex> b) {
        return b.stream().anyMatch(a::contains);
    }

    public List<Trade> sendOffer() {
        List<List<Product>> listCycles = getPaths();
        for (List<Product> cycle : listCycles) {
            for (int i = 0; i < cycle.size() - 1; i++) {
                Product product = cycle.get(i);
                product.setStatus(false);
                Trade trade = new Trade(product, cycle.get(i + 1), Instant.now());
                repository.save(trade);
            }
            cycle.get(cycle.size() - 1).setStatus(false);
            Trade trade = new Trade(cycle.get(cycle.size() - 1), cycle.get(0), Instant.now());
            repository.save(trade);
        }
        return repository.findAll();
    }
}
