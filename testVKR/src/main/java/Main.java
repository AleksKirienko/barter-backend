import java.util.*;

public class Main {

    private static boolean isContain(List<Vertex> a, List<Vertex> b) {
        for (Vertex i: b) {
            if (a.contains(i)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Product one = new Product("1");
        Product two = new Product("2");
        Product three = new Product("3");
        Product four = new Product("4");
        Product five = new Product("5");
        Product six = new Product("6");
        Product seven = new Product("7");
        Product eight = new Product("8");
        Product nine = new Product("9");
        Product ten = new Product("10");
        Product eleven = new Product("11");
        Product twelve = new Product("12");


        one.setProductsForExchange(new ArrayList<>(Arrays.asList(two, three, ten)));
        two.setProductsForExchange(new ArrayList<>());
        three.setProductsForExchange(new ArrayList<>(Arrays.asList(four, six)));
        four.setProductsForExchange(new ArrayList<>(Collections.singletonList(five)));
        five.setProductsForExchange(new ArrayList<>(Collections.singletonList(nine)));
        six.setProductsForExchange(new ArrayList<>(Collections.singletonList(seven)));
        seven.setProductsForExchange(new ArrayList<>(Arrays.asList(four, eight, eleven)));
        eight.setProductsForExchange(new ArrayList<>(Arrays.asList(nine, twelve)));
        nine.setProductsForExchange(new ArrayList<>(Collections.singletonList(twelve)));
        ten.setProductsForExchange(new ArrayList<>(Arrays.asList(two, eleven)));
        eleven.setProductsForExchange(new ArrayList<>(Collections.singletonList(seven)));
        twelve.setProductsForExchange(new ArrayList<>(Arrays.asList(seven, eleven)));


        List<Product> productList = new ArrayList<>(Arrays.asList(one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve));

        SearchPath s = new SearchPath(productList);

        s.buildMatrixHash();

        List<List<Vertex>> allCycles = s.getAllCycles();
        for (List<Vertex> cycle: allCycles) {
            for (Vertex vertex: cycle) {
                System.out.print(vertex.getProduct().getName() + " - ");
            }
            System.out.println();
        }

        int max = 0;
        List<Vertex> maxCyc = new ArrayList<>();
        for (List<Vertex> cycle: allCycles) {
            if (cycle.size() > max) {
                max = cycle.size();
                maxCyc = cycle;
            }
        }

        for (Vertex vertex: maxCyc) {
            System.out.print(vertex.getProduct().getName() + " - ");
        }
    }
}
