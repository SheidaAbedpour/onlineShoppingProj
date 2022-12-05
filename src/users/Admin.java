package users;

import exceptions.MyExceptions;
import products.Product;
import request.ProductRequest;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public final class Admin extends PersonalInformation {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<ProductRequest> productRequestsAdd = new ArrayList<>();
    private ArrayList<ProductRequest> productRequestEdit = new ArrayList<>();
    private ArrayList<ProductRequest> productRequestsRemove = new ArrayList<>();
    private ArrayList<Seller> sellerRequests = new ArrayList<>();
    private ArrayList<Seller> sellers = new ArrayList<>();
    private ArrayList<Consumer> consumers = new ArrayList<>();

    private static Admin admin;

    static {
        try {
            admin = new Admin("admin", "admin","admin.shopping@gmail.com",
                    "09131142365", "admin");
        } catch (MyExceptions | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Admin (String firstName, String lastName, String email, String phone, String password) throws MyExceptions, IOException, SQLException {
        super("Admin", firstName, lastName, email, phone, password,0,5000);
    }

    public static Admin getAdmin() {
        return admin;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
        product.insert();
    }

    public ArrayList<ProductRequest> getProductRequestsAdd() {
        return productRequestsAdd;
    }

    public void setProductRequestsAdd(ArrayList<ProductRequest> productRequests) {
        this.productRequestsAdd = productRequests;
    }

    public void setProductRequestsAdd(ProductRequest productRequest) {
        this.productRequestsAdd.add(productRequest);
        setProductRequestsAdd(productRequestsAdd);
    }

    public ArrayList<ProductRequest> getProductRequestEdit() {
        return productRequestEdit;
    }

    public void setProductRequestEdit(ProductRequest productRequest){
        this.productRequestEdit.add(productRequest);
    }

    public ArrayList<ProductRequest> getProductRequestsRemove() {
        return productRequestsRemove;
    }

    public void setProductRequestsRemove(ArrayList<ProductRequest> productRequestsRemove) {
        this.productRequestsRemove = productRequestsRemove;
    }

    public void setProductRequestsRemove(ProductRequest productRequestRemove) throws IOException {
        this.productRequestsRemove.add(productRequestRemove);
    }

    public void setSellers(Seller seller) {
        this.sellers.add(seller);
        seller.inset();
        seller.savePhone();
        seller.saveEmail();
    }

    public void setSellers(ArrayList<Seller> sellers){
        this.sellers = sellers;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setConsumers(Consumer consumer) {
        this.consumers.add(consumer);
        consumer.inset();
        consumer.savePhone();
        consumer.saveEmail();
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setSellerRequests(Seller sellerRequest) throws IOException {
        this.sellerRequests.add(sellerRequest);
    }

    public ArrayList<Seller> getSellerRequests() {
        return sellerRequests;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
