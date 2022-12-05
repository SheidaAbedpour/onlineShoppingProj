package users;

import exceptions.MyExceptions;
import factor.BuyFactor;
import mySQL.MySql;
import products.Product;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class Consumer extends PersonalInformation {

    private double credit;
    private ArrayList<BuyFactor> buyFactors = new ArrayList<>();
    private final ArrayList<Product> shop = new ArrayList<>();

    public Consumer (String firstName, String lastName, String email, String phone, String password,
                     double credit) throws MyExceptions, IOException, SQLException {

        super("Consumer", firstName, lastName, email, phone, password,credit, (int) (System.currentTimeMillis()/10000000) / 3);
        Admin.getAdmin().setConsumers(this);
    }

    public Consumer(){
        super("Consumer");
    }


    public void inset() {

        String cmd = String.format("INSERT INTO consumers(id,firstName,lastName,password,credit) VALUES(%d,'%s','%s','%s',%f)",
                getID(),getFirstName(),getLastName(),getPassword(),getCredit());
        new MySql().myExecuteSQL(cmd);
    }


    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
        String cmd = String.format("UPDATE consumers SET credit=%f WHERE id='%s'",getCredit(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    public ArrayList<BuyFactor> getBuyFactors() {
        return buyFactors;
    }

    public void setBuyFactors(ArrayList<BuyFactor> buyFactors) {
        this.buyFactors = buyFactors;
    }

    public void setBuyFactors(BuyFactor buyFactor) throws IOException {
        this.buyFactors.add(buyFactor);
    }

    public void setShop(Product shop) {
        this.shop.add(shop);
    }

    public ArrayList<Product> getShop() {
        return shop;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                super.toString() +
                ", credit=" + credit +
                '}';
    }
}
