package factor;

import products.Product;
import users.Seller;

import java.util.ArrayList;

public class BuyFactor {

    private final int ID;
    private static int id = 7000;
    private String Date;
    private double payed;
    private ArrayList<Product> products;
    private Seller seller;
    private boolean isDelivered;

    public BuyFactor (String date,double payed, ArrayList<Product> products, Seller seller, boolean isDelivered) {
        this.ID = id++;
        this.Date = date;
        this.payed = payed;
        this.products = products;
        this.seller = seller;
        this.isDelivered = isDelivered;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getID() {
        return ID;
    }

    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    @Override
    public String toString() {
        return "BuyFactor{" +
                "ID=" + ID +
                ", Date='" + Date + '\'' +
                ", payed=" + payed +
                ", seller=" + seller +
                ", isDelivered=" + isDelivered +
                '}';
    }
}
