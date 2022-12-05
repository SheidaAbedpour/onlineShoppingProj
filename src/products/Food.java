package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class Food extends Product {

    private String DateOfManufacture;
    private String ExpirationDate;

    public Food (String id, String name, String brand, double price, Seller seller, int number, String description,
                 String dateOfManufacture, String expirationDate) throws MyExceptions{

        super(id, name,brand, price, seller, number, description,ProductKind.FOOD);
        this.DateOfManufacture = dateOfManufacture;
        this.ExpirationDate = expirationDate;
        Category.Food.setProducts(this);
    }

    @Override
    public void changeInfo(Product newProduct) {
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setDateOfManufacture(((Food) newProduct).getDateOfManufacture());
        setExpirationDate(((Food) newProduct).getExpirationDate());

        String cmd = String.format("UPDATE food SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void insert() {
        String cmd = String.format("INSERT INTO food(id,name,brand,price,sellerID,number,description,date1,date2) VALUES(" +
                "'%s','%s','%s',%f,%d,%d,'%s','%s','%s')",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),getNumber(),
                getDescription(),DateOfManufacture,ExpirationDate);
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM food WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    public String getDateOfManufacture() {
        return DateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        DateOfManufacture = dateOfManufacture;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Food{" +
                super.toString() +
                ", DateOfManufacture=" + DateOfManufacture +
                ", ExpirationDate=" + ExpirationDate +
                '}';
    }
}
