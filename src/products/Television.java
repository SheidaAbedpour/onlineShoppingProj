package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class Television extends Furniture {

    private int quality;
    private String Size;

    public Television(String id, String name, String brand, double price, Seller seller, int number, String description,
                      String energyUsage, boolean isGuaranteed, int quality, String size) throws MyExceptions {

        super(id, name, brand, price, seller, number, description, energyUsage, isGuaranteed,ProductKind.TELEVISION);
        this.quality = quality;
        this.Size = size;
        Category.TV.setProducts(this);
    }

    @Override
    public void insert() {
        String cmd = String.format("INSERT INTO tv(id,name,brand,price,sellerID,number,description,energy,guarantee,quality,size) " +
                "VALUES('%s','%s','%s',%f,%d,%d,'%s','%s','%s',%d,'%s')",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),
                getNumber(),getDescription(),getEnergyUsage(),isGuaranteed(),quality,Size);
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM tv WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void changeInfo(Product newProduct) {
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setEnergyUsage(((Television) newProduct).getEnergyUsage());
        setGuaranteed(((Television) newProduct).isGuaranteed());
        setQuality(((Television) newProduct).quality);
        setSize(((Television) newProduct).Size);

        String cmd = String.format("UPDATE tv SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    @Override
    public String toString() {
        return "TV{" +
                super.toString() +
                ", quality=" + quality +
                ", Size=" + Size +
                '}';
    }
}
