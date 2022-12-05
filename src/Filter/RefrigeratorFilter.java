package Filter;

import category.Category;
import products.Product;
import products.Refrigerator;

import java.util.ArrayList;

public class RefrigeratorFilter extends FurnitureFilter {

    public RefrigeratorFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<Integer> capacityRange() {
        int min = ((Refrigerator)(getProducts().get(0))).getCapacity();
        int max = ((Refrigerator)(getProducts().get(0))).getCapacity();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Refrigerator) product).getCapacity() < min)
                min = ((Refrigerator)product).getCapacity();
            if (((Refrigerator)product).getCapacity() > max)
                max = ((Refrigerator)product).getCapacity();
        }

        range.add(min);
        range.add(max);

        return range;
    }

    public ArrayList<Product> capacityFilter(int min, int max) {
        ArrayList<Product> filter = new ArrayList<>();

        for (Product product : getProducts())
            if (capacityRange().get(0) <= ((Refrigerator)product).getCapacity() &&
                    ((Refrigerator)product).getCapacity() <= capacityRange().get(1))
                if (min <= ((Refrigerator)product).getCapacity() && ((Refrigerator)product).getCapacity() <= max)
                    filter.add(product);

        return filter;
    }

    public ArrayList<String> kindRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Refrigerator) product).getKind().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Refrigerator) product).getKind());
        }
        return range;
    }

    public ArrayList<Product> kindFilter(ArrayList<String> kind) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : kind)
                if (((Refrigerator) product).getKind().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public ArrayList<Product> freezerFilter() {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            if (((Refrigerator) product) .isHasFreezer())
                filter.add(product);
        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", capacity" +
                ", refrigeratorKind" +
                ", freezer" +
                '}';
    }
}
