package products;

import category.Category;
import exceptions.MyExceptions;
import users.Seller;

public abstract class Wearable extends Product {

    private String Country;
    private String Material;

    Wearable (String id,String name, String brand, double price, Seller seller, int number, String description,
              String country, String material,ProductKind productKind) throws MyExceptions {

        super(id, name, brand, price, seller, number, description,productKind);
        this.Country = country;
        this.Material = material;
        Category.Wearable.setProducts(this);
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Country=" + Country +
                ", Material=" + Material;
    }
}
