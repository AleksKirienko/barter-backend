import java.util.List;

public class Product {
    String name;
    List<Product> productsForExchange;

    public Product(String name, List<Product> productsForExchange) {
        this.name = name;
        this.productsForExchange = productsForExchange;
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductsForExchange() {
        return productsForExchange;
    }

    public void setProductsForExchange(List<Product> productsForExchange) {
        this.productsForExchange = productsForExchange;
    }
}
