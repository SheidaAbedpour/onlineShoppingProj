package products;

import category.Category;
import exceptions.MyExceptions;
import users.Seller;

public abstract class Digital extends Product {

    private int memory;
    private int ram;
    private String OS;
    private double weight;
    private String Size;

    Digital (String id, String name, String brand, double price, Seller seller, int number, String description,
             int memory, int ram, String OS, double weight, String Size,ProductKind productKind) throws MyExceptions {

        super(id, name,brand, price, seller, number, description,productKind);
        this.memory = memory;
        this.ram = ram;
        this.OS = OS;
        this.weight = weight;
        this.Size = Size;
        Category.Digital.setProducts(this);
    }


    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", memory=" + memory +
                ", ram=" + ram +
                ", OS=" + OS +
                ", weight=" + weight +
                ", Size=" + Size;
    }
}
