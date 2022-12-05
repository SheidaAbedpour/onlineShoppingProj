package users;

import exceptions.MyExceptions;
import factor.SellingFactor;
import mySQL.MySql;
import pages.SellerGUI;
import products.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public final class Seller extends PersonalInformation implements Serializable {

    private double credit;
    private String Company;
    private ArrayList<SellingFactor> sellingFactors = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private SellerGUI sellerGUI;
    private boolean accepted = false;

    public Seller (String firstName, String lastName, String email, String phone, String password, double credit)
            throws MyExceptions, IOException, SQLException {
        super("Seller", firstName, lastName, email, phone, password,credit, (int) (31*System.currentTimeMillis()/100000)/35);
        setCredit(credit);
        Admin.getAdmin().setSellerRequests(this);
    }


    public Seller() {
        super("Seller");
    }

    public void inset() {

        String cmd = String.format("INSERT INTO sellers(id,firstName,lastName,password,credit) VALUES(%d,'%s','%s','%s',%f)",
                getID(),getFirstName(),getLastName(),getPassword(),getCredit());
        new MySql().myExecuteSQL(cmd);
    }

    public void remove() {
        String cmd = String.format("DELETE FROM sellers WHERE id=%d",getID());
        new MySql().myExecuteSQL(cmd);
        String cmd2 = String.format("DELETE FROM emails WHERE userID=%d",getID());
        new MySql().myExecuteSQL(cmd2);
        String cmd3 = String.format("DELETE FROM phones WHERE userID=%d",getID());
        new MySql().myExecuteSQL(cmd3);
    }


    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
        String cmd = String.format("UPDATE sellers SET credit=%f WHERE id='%s'",credit,getID());
        new MySql().myExecuteSQL(cmd);
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public ArrayList<SellingFactor> getSellingFactors() {
        return sellingFactors;
    }

    public void setSellingFactors(ArrayList<SellingFactor> sellingFactors) {
        this.sellingFactors = sellingFactors;
    }

    public void setSellingFactors(SellingFactor sellingFactor) throws IOException {
        this.sellingFactors.add(sellingFactor);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product)  {
        this.products.add(product);
    }

    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }

    public SellerGUI getSellerGUI() {
        return sellerGUI;
    }

    public void setSellerGUI(SellerGUI sellerGUI) {
        this.sellerGUI = sellerGUI;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Seller{" +
                super.toString() +
                ", credit=" + credit +
                ", Company=" + Company +
                '}';
    }

}
