package Filter;

import category.Category;
import products.Digital;
import products.Furniture;
import products.Product;

import java.util.ArrayList;

public abstract class FurnitureFilter extends ProductFilter {

    public FurnitureFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<String> energyRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            boolean add = false;
            for (String s : range)
                if (((Furniture) product).getEnergyUsage().equals(s)) {
                    add = true;
                    break;
                }
            if (!add)
                range.add(((Furniture) product).getEnergyUsage());
        }
        return range;
    }

    public ArrayList<Product> energyUsageFilter(ArrayList<String> energy) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : energy)
                if (((Furniture) product).getEnergyUsage().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public ArrayList<Product> guaranteeFilter() {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            if (((Furniture) product).isGuaranteed())
                filter.add(product);
        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", energyUsage" +
                ", guaranteed";
    }
}
