package Filter;

import category.Category;
import products.Product;
import products.Wearable;

import java.util.ArrayList;

public abstract class WearableFilter extends ProductFilter {

    public WearableFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<String> countryRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Wearable) product).getCountry().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Wearable) product).getCountry());
        }
        return range;
    }

    public ArrayList<Product> countryFilter(ArrayList<String> country) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : country)
                if (((Wearable) product).getCountry().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public ArrayList<String> materialRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Wearable) product).getMaterial().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Wearable) product).getMaterial());
        }
        return range;
    }

    public ArrayList<Product> materialFilter(ArrayList<String> material) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : material)
                if (((Wearable) product).getMaterial().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public String toString1(){
        return super.toString1() +
                ", country" +
                ", material";
    }
}
