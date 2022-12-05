package products;

import category.Category;
import comments.Comment;
import comments.Rank;
import exceptions.MyExceptions;
import users.Admin;
import users.Seller;

import java.io.*;
import java.util.ArrayList;

public abstract class Product implements Comparable {

    private final String ID;
    private String Name;
    private final ProductKind productKind;
    private String Brand;
    private double price;
    private Seller seller;
    private boolean inventoryStatus;
    private int number;
    private String Description;
    private double averageRank;
    private ArrayList<Rank> ranks = new ArrayList<Rank>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private boolean isAccepted = false;

    Product (String ID, String name,String brand, double price, Seller seller, int number, String description,
             ProductKind productKind) throws MyExceptions {

        for (Product product : Admin.getAdmin().getProducts())
            if (product.getID().equals(ID))
                throw new MyExceptions("try another username");

        this.Name = name;
        this.ID = ID;
        this.Brand = brand;
        this.price = price;
        this.seller = seller;
        this.number = number;
        this.Description = description;
        this.productKind = productKind;
        Category.Products.setProducts(this);
    }

    public abstract void insert();

    public abstract void remove();

    public abstract void changeInfo(Product product);

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public ProductKind getProductKind() {
        return productKind;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isInventoryStatus() {
        setInventoryStatus();
        return inventoryStatus;
    }

    public void setInventoryStatus(boolean inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public void setInventoryStatus() {
        if (number > 0)
            inventoryStatus = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getAverageRank() {
        setAverageRank();
        return averageRank;
    }

    public void setAverageRank(double averageRank) {
        this.averageRank = averageRank;
    }

    public void setAverageRank() {
        if (this.ranks.size() == 0) {
            averageRank = 0;
            return;
        }

        double sum = 0;
        for (Rank rank : ranks)
            sum += rank.getRank();
        averageRank = sum / ranks.size();

        setAverageRank(averageRank);

    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setComments(Comment comment) throws IOException {
        this.comments.add(comment);
    }

    public ArrayList<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(ArrayList<Rank> ranks) throws IOException {
        this.ranks = ranks;
        setAverageRank();
    }

    public void setRanks(Rank rank) throws IOException {
        this.ranks.add(rank);
        setAverageRank();
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }


    @Override
    public int compareTo(Object obj) {
        Product product = (Product) obj;

        if (this.productKind.ordinal() < product.productKind.ordinal())
            if (this.Name.compareTo(product.Name) > 0)
                if (this.averageRank > product.averageRank)
                    if (this.price > product.price)
                        if (this.isInventoryStatus() && !product.isInventoryStatus())
                            return 1;

        if (this.productKind.ordinal() == product.productKind.ordinal())
            if (this.Name.compareTo(product.Name) == 0)
                if (this.averageRank == product.averageRank)
                    if (this.price == product.price)
                        if ((this.isInventoryStatus() && product.isInventoryStatus()) ||
                                (!this.isInventoryStatus() && !product.isInventoryStatus()))
                            return 0;

        return -1;

    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", Brand=" + Brand +
                ", price=" + price +
                ", seller=" + seller +
                ", rank=" + getAverageRank() +
                ", inventoryStatus=" + inventoryStatus;
    }
}
