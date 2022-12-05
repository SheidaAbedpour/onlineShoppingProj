package Filter;

import category.Category;
import products.Product;
import products.Television;

import java.util.ArrayList;

public class TelevisionFilter extends FurnitureFilter {

    public TelevisionFilter (ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<Integer> qualityRange() {
        int min = ((Television)(getProducts().get(0))).getQuality();
        int max = ((Television)(getProducts().get(0))).getQuality();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Television) product).getQuality() < min)
                min = ((Television)product).getQuality();
            if (((Television)product).getQuality() > max)
                max = ((Television)product).getQuality();
        }

        range.add(min);
        range.add(max);

        return range;
    }

    public ArrayList<Product> qualityFilter(int min, int max) {
        ArrayList<Product> filter = new ArrayList<>();

        for (Product product : getProducts())
            if (qualityRange().get(0) <= ((Television)product).getQuality() &&
                    ((Television)product).getQuality() <= qualityRange().get(1))
                if (min <= ((Television)product).getQuality() && ((Television)product).getQuality() <= max)
                    filter.add(product);

        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", quality" +
                '}';
    }
}
