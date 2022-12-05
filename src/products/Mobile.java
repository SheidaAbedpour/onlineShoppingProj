package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


final public class Mobile extends Digital {

    private int simCards;
    private int cameraQuality;

    public Mobile (String id, String name, String brand, double price, Seller seller, int number, String description,
                   int memory, int ram, String OS, double weight, String Size, int simCards, int cameraQuality) throws MyExceptions {

        super(id, name,brand, price, seller, number, description, memory, ram, OS, weight, Size,ProductKind.MOBILE);
        this.simCards = simCards;
        this.cameraQuality = cameraQuality;
        Category.Mobile.setProducts(this);
    }


    @Override
    public void changeInfo(Product newProduct) {
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setMemory(((Mobile)newProduct).getMemory());
        setRam(((Mobile) newProduct).getRam());
        setOS(((Mobile) newProduct).getOS());
        setWeight(((Mobile)newProduct).getWeight());
        setSize(((Mobile) newProduct).getSize());
        setSimCards(((Mobile) newProduct).getSimCards());
        setCameraQuality(((Mobile) newProduct).cameraQuality);

        String cmd = String.format("UPDATE mobile SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void insert() {
        String cmd = String.format("INSERT INTO mobile(id,name,brand,price,sellerID,number,description,memory,ram,os,weight,size,sim,camera) " +
                "VALUES('%s','%s','%s',%f,%d,%d,'%s',%d,%d,'%s',%f,'%s',%d,%d)",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),
                getNumber(),getDescription(),getMemory(),getRam(),getOS(),getWeight(),getSize(),simCards,cameraQuality);
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM mobile WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    public int getSimCards() {
        return simCards;
    }

    public void setSimCards(int simCards) {
        this.simCards = simCards;
    }

    public int getCameraQuality() {
        return cameraQuality;
    }

    public void setCameraQuality(int cameraQuality) {
        this.cameraQuality = cameraQuality;
    }


    @Override
    public String toString() {
        return "Mobile{" +
                super.toString() +
                ", simCards=" + simCards +
                ", cameraQuality=" + cameraQuality +
                '}';
    }
}
