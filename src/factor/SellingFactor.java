package factor;

import products.Product;
import users.Consumer;

import java.util.ArrayList;

public class SellingFactor {

    private final int ID;
    private static int id = 8000;
    private String Date;
    private double payed;
    private ArrayList<Product> products;
    private Consumer consumer;
    private boolean isSend;

    public SellingFactor (String date, double payed, ArrayList<Product> products, Consumer consumer, boolean isSend) {
        this.ID = id++;
        this.Date = date;
        this.payed = payed;
        this.products = products;
        this.consumer = consumer;
        this.isSend = isSend;
    }

    public int getID() {
        return ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    @Override
    public String toString() {
        return "SellingFactor{" +
                "ID=" + ID +
                ", Date='" + Date + '\'' +
                ", payed=" + payed +
                ", consumer=" + consumer +
                ", isSend=" + isSend +
                '}';
    }
}
