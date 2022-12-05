package Filter;

import category.Category;
import products.Clothes;
import products.Product;

import java.util.ArrayList;

public class ClothesFilter extends WearableFilter {

    public ClothesFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<String> sizeRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Clothes) product).getSize().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Clothes) product).getSize());
        }
        return range;
    }

    public ArrayList<Product> sizeFilter(ArrayList<String> size) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : size)
                if (((Clothes) product).getSize().equals(s)) {
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
                if (((Clothes) product).getKind().toString().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Clothes) product).getKind().toString());
        }
        return range;
    }

    public ArrayList<Product> kindFilter(ArrayList<String> kind) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : kind)
                if (((Clothes) product).getKind().toString().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", clothesSize" +
                ", clothesKind" +
                '}';
    }
}
