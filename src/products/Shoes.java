package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class Shoes extends Wearable {

    private int size;
    private shoeKind kind;

    public Shoes (String id, String name, String brand, double price, Seller seller, int number, String description,
                  String country, String material, int size, String kind) throws MyExceptions {

        super(id, name, brand, price, seller, number, description, country, material,ProductKind.SHOES);
        this.size = size;
        this.kind = shoeKind.valueOf(kind);
        Category.Shoes.setProducts(this);
    }


    @Override
    public void insert() {
        String cmd = String.format("INSERT INTO shoes(id,name,brand,price,sellerID,number,description,country,material,size,kind)" +
                "VALUES('%S','%S','%S',%f,%d,%d,'%s','%s','%s',%d,'%s')",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),
                getNumber(),getDescription(),getCountry(),getMaterial(),size,kind);
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM shoes WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void changeInfo(Product newProduct)  {
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setCountry(((Shoes) newProduct).getCountry());
        setKind(((Shoes) newProduct).getKind());

        String cmd = String.format("UPDATE shoes SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public shoeKind getKind() {
        return kind;
    }

    public void setKind(shoeKind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                super.toString() +
                ", size=" + size +
                ", kind=" + kind +
                '}';
    }
}


