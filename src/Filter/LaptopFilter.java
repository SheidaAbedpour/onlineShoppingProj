package Filter;

import category.Category;
import products.Laptop;
import products.Product;

import java.util.ArrayList;

public class LaptopFilter extends DigitalFilter {

    public LaptopFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<String> cpuRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Laptop) product).getCPU().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Laptop) product).getCPU());
        }
        return range;
    }

    public ArrayList<Product> cpuFilter(ArrayList<String> cpu) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : cpu)
                if (((Laptop) product).getCPU().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public ArrayList<Product> gamingFilter() {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            if (((Laptop) product).isGaming())
                filter.add(product);
        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", cpu" +
                ", gaming" +
                '}';
    }
}
