package Filter;

import category.Category;
import products.Product;
import products.Stove;

import java.util.ArrayList;

public class StoveFilter extends FurnitureFilter {

    public StoveFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<Integer> flamesRange() {
        int min = ((Stove)(getProducts().get(0))).getFlamesNumber();
        int max = ((Stove)(getProducts().get(0))).getFlamesNumber();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Stove) product).getFlamesNumber() < min)
                min = ((Stove)product).getFlamesNumber();
            if (((Stove)product).getFlamesNumber() > max)
                max = ((Stove)product).getFlamesNumber();
        }

        range.add(min);
        range.add(max);

        return range;
    }

    public ArrayList<Product> flamesFilter(int min, int max) {
        ArrayList<Product> filter = new ArrayList<>();

        for (Product product : getProducts())
            if (flamesRange().get(0) <= ((Stove)product).getFlamesNumber() &&
                    ((Stove)product).getFlamesNumber() <= flamesRange().get(1))
                if (min <= ((Stove)product).getFlamesNumber() && ((Stove)product).getFlamesNumber() <= max)
                    filter.add(product);

        return filter;
    }

    public ArrayList<String> kindRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Stove) product).getKind().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Stove) product).getKind());
        }
        return range;
    }

    public ArrayList<Product> kindFilter(ArrayList<String> kind) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : kind)
                if (((Stove) product).getKind().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public ArrayList<Product> ferFilter() {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            if (((Stove) product).isHasFer())
                filter.add(product);
        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", flamesNumber" +
                ", stoveKind" +
                ", fer" +
                '}';
    }
}
