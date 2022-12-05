package products;

import category.Category;
import exceptions.MyExceptions;
import users.Seller;

abstract public class Furniture extends Product {

    private String EnergyUsage;
    private boolean isGuaranteed;

    Furniture (String id, String name, String brand, double price, Seller seller, int number, String description,
               String energyUsage, boolean isGuaranteed, ProductKind productKind) throws MyExceptions {

        super(id,name,brand, price, seller, number, description,productKind);
        this.EnergyUsage = energyUsage;
        this.isGuaranteed = isGuaranteed;
        Category.Furniture.setProducts(this);
    }

    public String getEnergyUsage() {
        return EnergyUsage;
    }

    public void setEnergyUsage(String energyUsage) {
        EnergyUsage = energyUsage;
    }

    public boolean isGuaranteed() {
        return isGuaranteed;
    }

    public void setGuaranteed(boolean guaranteed) {
        isGuaranteed = guaranteed;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", EnergyUsage=" + EnergyUsage +
                ", isGuaranteed=" + isGuaranteed;
    }
}
