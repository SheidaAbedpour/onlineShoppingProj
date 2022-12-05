package pages;


import exceptions.MyExceptions;
import guiProducts.MainPageShow;
import mySQL.MySql;
import products.*;
import users.Admin;
import users.Consumer;
import users.Seller;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPage {

    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {

        try {
            loadSellers();
            loadConsumers();

            loadClothes();
            loadShoes();
            loadTV();
            loadRefrigerator();
            loadStove();
            loadMobile();
            loadLaptop();
            loadFood();
        }catch (SQLException | MyExceptions exception){
            JOptionPane.showInputDialog(exception.getMessage());
        }

        Admin.getAdmin().setProducts(products);

        new MainPageShow(null);


    }

    public static void saveExceptions(Exception e) {
        String cmd = String.format("INSERT INTO exceptions(exception) VALUES('%S')",e.getMessage());
        new MySql().myExecuteSQL(cmd);
    }

    public static void loadSellers() throws SQLException {

        String cmd = "SELECT * FROM (sellers INNER JOIN phones ON sellers.id = phones.userID) INNER JOIN emails ON sellers.id = emails.userID";

        ArrayList<Seller> sellers = new ArrayList<>();

        ResultSet resultSet = new MySql().myExecuteQuery(cmd);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String pass = resultSet.getString("password");
            double credit = resultSet.getDouble("credit");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");

            Seller seller = new Seller();
            seller.setID(id);
            seller.setFirstName(firstName);
            seller.setLastName(lastName);
            seller.setPassword(pass);
            seller.setEmail(email);
            seller.setCredit(credit);
            seller.setPhone(phone);
            seller.setAccepted(true);

            sellers.add(seller);
        }

        Admin.getAdmin().setSellers(sellers);
    }

    public static void loadConsumers() throws SQLException {

        String cmd = "SELECT * FROM (consumers INNER JOIN phones ON consumers.id = phones.userID) INNER JOIN emails ON consumers.id = emails.userID";

        ArrayList<Consumer> consumers = new ArrayList<>();

        ResultSet resultSet = new MySql().myExecuteQuery(cmd);
        while (resultSet.next()){

            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String pass = resultSet.getString("password");
            double credit = resultSet.getDouble("credit");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");

            Consumer consumer = new Consumer();
            consumer.setID(id);
            consumer.setFirstName(firstName);
            consumer.setLastName(lastName);
            consumer.setPassword(pass);
            consumer.setEmail(email);
            consumer.setCredit(credit);
            consumer.setPhone(phone);

            consumers.add(consumer);
        }

        Admin.getAdmin().setConsumers(consumers);

    }


    public static void loadClothes() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM clothes";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            String country = resultSet.getString("country");
            String material = resultSet.getString("material");
            String size = resultSet.getString("size");
            String kind = resultSet.getString("kind");

            Seller seller = getSeller(sellerID);
            Clothes clothes = new Clothes(id,name,brand,price,seller,number,description,country,material,size,kind);
            products.add(clothes);
            if (seller != null) {
                seller.setProducts(clothes);
            }
        }

    }

    public static void loadShoes() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM shoes";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            String country = resultSet.getString("country");
            String material = resultSet.getString("material");
            int size = resultSet.getInt("size");
            String kind = resultSet.getString("kind");

            Seller seller = getSeller(sellerID);
            Shoes shoes = new Shoes(id,name,brand,price,seller,number,description,country,material,size,kind);
            products.add(shoes);
            if (seller != null) {
                seller.setProducts(shoes);
            }
        }

    }

    public static void loadTV() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM tv";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            String energy = resultSet.getString("energy");
            boolean guarantee = resultSet.getBoolean("guarantee");
            int quality = resultSet.getInt("quality");
            String size = resultSet.getString("size");

            Seller seller = getSeller(sellerID);
            Television tv = new Television(id,name,brand,price,seller,number,description,energy,guarantee,quality,size);
            products.add(tv);
            if (seller != null) {
                seller.setProducts(tv);
            }
        }

    }

    public static void loadRefrigerator() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM refrigerator";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            String energy = resultSet.getString("energy");
            boolean guarantee = resultSet.getBoolean("guarantee");
            int capacity = resultSet.getInt("capacity");
            String kind = resultSet.getString("kind");
            boolean freezer = resultSet.getBoolean("freezer");

            Seller seller = getSeller(sellerID);
            Refrigerator refrigerator = new Refrigerator(id,name,brand,price,seller,number,description,energy,guarantee,capacity,kind,freezer);
            products.add(refrigerator);
            if (seller != null) {
                seller.setProducts(refrigerator);
            }
        }

    }

    public static void loadStove() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM stove";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            String energy = resultSet.getString("energy");
            boolean guarantee = resultSet.getBoolean("guarantee");
            int flame = resultSet.getInt("flame");
            String kind = resultSet.getString("kind");
            boolean fer = resultSet.getBoolean("fer");

            Seller seller = getSeller(sellerID);
            Stove stove = new Stove(id,name,brand,price,seller,number,description,energy,guarantee,flame,kind,fer);
            products.add(stove);
            if (seller != null) {
                seller.setProducts(stove);
            }
        }
    }

    public static void loadMobile() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM mobile";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            int memory = resultSet.getInt("memory");
            int ram = resultSet.getInt("ram");
            String os = resultSet.getString("os");
            double weight = resultSet.getDouble("weight");
            String size = resultSet.getString("size");
            int sim = resultSet.getInt("sim");
            int camera = resultSet.getInt("camera");

            Seller seller = getSeller(sellerID);
            Mobile mobile = new Mobile(id,name,brand,price,seller,number,description,memory,ram,os,weight,size,sim,camera);
            products.add(mobile);
            if (seller != null) {
                seller.setProducts(mobile);
            }
        }

    }

    public static void loadLaptop() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM laptop";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            int memory = resultSet.getInt("memory");
            int ram = resultSet.getInt("ram");
            String os = resultSet.getString("os");
            double weight = resultSet.getDouble("weight");
            String size = resultSet.getString("size");
            String cpu = resultSet.getString("cpu");
            boolean gaming = resultSet.getBoolean("gaming");

            Seller seller = getSeller(sellerID);
            Laptop laptop = new Laptop(id,name,brand,price,seller,number,description,memory,ram,os,weight,size,cpu,gaming);
            products.add(laptop);
            if (seller != null) {
                seller.setProducts(laptop);
            }
        }

    }

    public static void loadFood() throws SQLException, MyExceptions {

        String cmd = "SELECT * FROM food";
        ResultSet resultSet = new MySql().myExecuteQuery(cmd);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            double price = resultSet.getDouble("price");
            int sellerID = resultSet.getInt("sellerID");
            int number = resultSet.getInt("number");
            String description = resultSet.getString("description");
            String data1 = resultSet.getString("date1");
            String data2 = resultSet.getString("date2");

            Seller seller = getSeller(sellerID);
            Food food = new Food(id,name,brand,price,seller,number,description,data1,data2);
            products.add(food);
            if (seller != null) {
                seller.setProducts(food);
            }
        }

    }


    private static Seller getSeller(int id) {
        for (Seller seller : Admin.getAdmin().getSellers())
            if (seller.getID() == id)
                return seller;
        return null;
    }


}
