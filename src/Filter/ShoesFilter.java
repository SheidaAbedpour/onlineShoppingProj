package Filter;

import products.Product;
import products.Shoes;

import java.util.ArrayList;

public class ShoesFilter extends WearableFilter {

    public ShoesFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<Integer> sizeRange() {
        int min = ((Shoes)(getProducts().get(0))).getSize();
        int max = ((Shoes)(getProducts().get(0))).getSize();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Shoes) product).getSize() < min)
                min = ((Shoes)product).getSize();
            if (((Shoes)product).getSize() > max)
                max = ((Shoes)product).getSize();
        }

        range.add(min);
        range.add(max);

        return range;
    }

    public ArrayList<Product> sizeFilter(ArrayList<Integer> size) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (int s : size)
                if (((Shoes) product).getSize() == s) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public ArrayList<String> kindRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Shoes) product).getKind().toString().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Shoes) product).getKind().toString());
        }
        return range;
    }

    public ArrayList<Product> kindFilter(ArrayList<String> kind) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : kind)
                if (((Shoes) product).getKind().toString().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", shoeSize" +
                ", shoeKind" +
                '}';
    }
}
