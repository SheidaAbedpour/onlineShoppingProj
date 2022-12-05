package category;

import products.Product;

import java.util.ArrayList;

public enum Category {

    Products("Product"),
    Digital("Digital"), Mobile("Mobile"), Laptop("Laptop"),
    Wearable("Wearable"), Clothes("Clothes"), Shoes("Shoes"),
    Furniture("Furniture"), TV("TV"), Refrigerator("Refrigerator"), Stove("Stove"),
    Food("Food");

    private String Name;
    private ArrayList<Product> products = new ArrayList<>();

    Category (String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Category{" +
                "Name='" + Name + '\'' +
                '}';
    }
}
