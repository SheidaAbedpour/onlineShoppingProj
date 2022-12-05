package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class Refrigerator extends Furniture {

    private int capacity;
    private String kind;
    private boolean hasFreezer;

    public Refrigerator (String id, String name, String brand, double price, Seller seller, int number, String description,
                         String energyUsage, boolean isGuaranteed, int capacity, String kind, boolean hasFreezer) throws MyExceptions {

        super(id, name,brand, price, seller, number, description, energyUsage, isGuaranteed,ProductKind.REFRIGERATOR);
        this.capacity = capacity;
        this.kind = kind;
        this.hasFreezer = hasFreezer;
        Category.Refrigerator.setProducts(this);
    }

    @Override
    public void insert() {
        String cmd = String.format("INSERT INTO refrigerator(id,brand,price,sellerID,number,description,energy,guarantee,capacity,kind,freezer) " +
                "VALUES('%s','%s','%s',%f,%d,%d,'%s','%s','%s',%d,'%s','%s')",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),
                getNumber(),getDescription(),getEnergyUsage(),isGuaranteed(),capacity,kind,hasFreezer);
        new MySql().myExecuteSQL(cmd);
    }


    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM refrigerator WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void changeInfo(Product newProduct)  {
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setEnergyUsage(((Refrigerator) newProduct).getEnergyUsage());
        setGuaranteed(((Refrigerator) newProduct).isGuaranteed());
        setCapacity(((Refrigerator) newProduct).getCapacity());
        setKind(((Refrigerator) newProduct).kind);
        setHasFreezer(((Refrigerator) newProduct).hasFreezer);

        String cmd = String.format("UPDATE refrigerator SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isHasFreezer() {
        return hasFreezer;
    }

    public void setHasFreezer(boolean hasFreezer) {
        this.hasFreezer = hasFreezer;
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                super.toString() +
                ", capacity=" + capacity +
                ", kind=" + kind +
                ", hasFreezer=" + hasFreezer +
                '}';
    }
}
