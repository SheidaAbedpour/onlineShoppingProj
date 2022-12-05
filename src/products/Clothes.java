package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class Clothes extends Wearable {

    private String Size;
    private clothesKind kind;

    public Clothes (String id, String name,String brand, double price, Seller seller, int number, String description,
             String country, String material, String size, String kind) throws MyExceptions {

        super(id, name,brand, price, seller, number, description, country, material,ProductKind.CLOTHES);
        this.Size = size;
        this.kind = clothesKind.valueOf(kind);
        Category.Clothes.setProducts(this);
    }

    public void insert() {
        String cmd = String.format("INSERT INTO clothes(id,name,brand,price,sellerID,number,description,country,material,size,kind) " +
                "VALUES('%s','%s','%s',%f,%d,%d,'%s','%s','%s','%s','%s')",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),
                getNumber(),getDescription(),getCountry(),getMaterial(),Size,kind);
        new MySql().myExecuteSQL(cmd);
    }


    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM clothes WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void changeInfo(Product newProduct) {
        setName(newProduct.getName());
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setCountry(((Clothes) newProduct).getCountry());
        setMaterial(((Clothes) newProduct).getMaterial());
        setSize(((Clothes) newProduct).getSize());
        setKind(((Clothes) newProduct).getKind());

        String cmd = String.format("UPDATE clothes SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public clothesKind getKind() {
        return kind;
    }

    public void setKind(clothesKind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Size=" + Size +
                ", kind=" + kind;
    }
}


