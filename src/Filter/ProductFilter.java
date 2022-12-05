package Filter;

import category.Category;
import products.Product;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ProductFilter {

    private ArrayList<Product> products;

    public ProductFilter (ArrayList<Product> products) {
        this.products = products;
    }

    private void setProducts (Category category) {
        for (Product product : category.getProducts())
            if (product.isAccepted())
                products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Double> priceRange () {
        double max = products.get(0).getPrice();
        double min = products.get(0).getPrice();
        ArrayList<Double> productsRange = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() < min)
                min = product.getPrice();
            if (product.getPrice() > max)
                max = product.getPrice();
        }

        productsRange.add(min);
        productsRange.add(max);

        return productsRange;
    }

    public  ArrayList<Product> priceFilter(double min, double max) {
        ArrayList<Double> range = priceRange();
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : products)
            if (range.get(0) <= product.getPrice() && product.getPrice() <= range.get(1))
                if (min <= product.getPrice() && product.getPrice() <= max)
                    filter.add(product);
        return filter;
    }

    public ArrayList<Product> inventoryFilter() {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : products)
            if (product.isInventoryStatus())
                filter.add(product);
        return filter;
    }

    public ArrayList<Product> rankFilter(double min, double max) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : products) {
            if (min <= product.getAverageRank() && product.getAverageRank() <= max)
                filter.add(product);
        }
        return filter;
    }
    
    public  String toString1() {
        return "price" +
                ", inventoryStatus" +
                ", averageRank";
    }
}
