package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class Stove extends Furniture {

    private int flamesNumber;
    private String kind;
    private boolean hasFer;

    public Stove (String id, String name, String brand, double price, Seller seller, int number, String description,
                  String energyUsage, boolean isGuaranteed, int flamesNumber, String kind, boolean hasFer) throws MyExceptions {

        super(id, name, brand, price, seller, number, description, energyUsage, isGuaranteed,ProductKind.STOVE);
        this.flamesNumber = flamesNumber;
        this.kind = kind;
        this.hasFer = hasFer;
        Category.Stove.setProducts(this);
    }

    @Override
    public void insert() {
        String cmd = String.format("INSERT INTO stove(id,name,brand,price,sellerID,number,description,energy,guarantee,flame,kind,fer)" +
                " VALUES('%s','%s','%s',%f,%d,%d,'%s','%s','%s',%d,'%s','%s')",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),
                getNumber(),getDescription(),getEnergyUsage(),isGuaranteed(),flamesNumber,kind,hasFer);
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM stove WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void changeInfo(Product newProduct) {
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setEnergyUsage(((Stove) newProduct).getEnergyUsage());
        setGuaranteed(((Stove) newProduct).isGuaranteed());
        setFlamesNumber(((Stove) newProduct).getFlamesNumber());
        setKind(((Stove) newProduct).getKind());
        setHasFer(((Stove) newProduct).hasFer);

        String cmd = String.format("UPDATE stove SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    public int getFlamesNumber() {
        return flamesNumber;
    }

    public void setFlamesNumber(int flamesNumber) {
        this.flamesNumber = flamesNumber;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isHasFer() {
        return hasFer;
    }

    public void setHasFer(boolean hasFer) {
        this.hasFer = hasFer;
    }

    @Override
    public String toString() {
        return "Stove{" +
                super.toString() +
                ", flamesNumber=" + flamesNumber +
                ", kind=" + kind +
                ", hasFer=" + hasFer +
                '}';
    }
}
