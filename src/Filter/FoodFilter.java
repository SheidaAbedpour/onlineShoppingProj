package Filter;

import products.Product;

import java.util.ArrayList;

public class FoodFilter extends  ProductFilter{

    public FoodFilter(ArrayList<Product> products) {
        super(products);
    }

    public String toString1() {
        return super.toString1() +
                ", date" +
                '}';
    }
}
