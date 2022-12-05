package products;

import category.Category;
import exceptions.MyExceptions;
import mySQL.MySql;
import users.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

final public class Laptop extends Digital {

    private String CPU;
    private boolean isGaming;

    public Laptop (String id, String name, String brand, double price, Seller seller, int number, String description,
                   int memory, int ram, String OS, double weight, String Size, String cpu, boolean isGaming) throws MyExceptions {

        super(id, name,brand, price, seller, number, description, memory, ram, OS, weight, Size,ProductKind.LAPTOP);
        this.CPU = cpu;
        this.isGaming = isGaming;
        Category.Laptop.setProducts(this);
    }


    @Override
    public void insert() {
        String cmd = String.format("INSET INTO laptop(id,name,brand,price,sellerID,number,description,memory,ram,os,weight,size,cpu,gaming) " +
                "VALUES('%s','%s','%s',%f,%d,%d,'%s',%d,%d,'%s',%f,'%s','%s','%s')",getID(),getName(),getBrand(),getPrice(),getSeller().getID(),
                getNumber(),getDescription(),getMemory(),getRam(),getOS(),getWeight(),getSize(),CPU,String.valueOf(isGaming));
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void remove() {
        String cmd = String.format("DELETE FROM laptop WHERE id='%s'",getID());
        new MySql().myExecuteSQL(cmd);
    }

    @Override
    public void changeInfo(Product newProduct) {
        setBrand(newProduct.getBrand());
        setPrice(newProduct.getPrice());
        setInventoryStatus(newProduct.isInventoryStatus());
        setDescription(newProduct.getDescription());
        setMemory(((Laptop)newProduct).getMemory());
        setRam(((Laptop) newProduct).getRam());
        setOS(((Laptop) newProduct).getOS());
        setWeight(((Laptop)newProduct).getWeight());
        setSize(((Laptop) newProduct).getSize());
        setCPU(((Laptop) newProduct).getCPU());
        setGaming(((Laptop) newProduct).isGaming);

        String cmd = String.format("UPDATE laptop SET price=%f WHERE id='%s'",getPrice(),getID());
        new MySql().myExecuteSQL(cmd);
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public boolean isGaming() {
        return isGaming;
    }

    public void setGaming(boolean gaming) {
        isGaming = gaming;
    }


    @Override
    public String toString() {
        return "Laptop{" +
                super.toString() +
                ", CPU=" + CPU +
                ", isGaming=" + isGaming +
                '}';
    }
}
