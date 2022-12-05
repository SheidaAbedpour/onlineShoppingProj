package Filter;

import category.Category;
import products.Digital;
import products.Product;

import java.util.ArrayList;

public abstract class DigitalFilter extends ProductFilter {

    DigitalFilter (ArrayList<Product> products) {
        super(products);
    }

    public  ArrayList<Integer> memoryRange() {
        int min = ((Digital)(getProducts().get(0))).getMemory();
        int max = ((Digital)(getProducts().get(0))).getMemory();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Digital) product).getMemory() < min)
                min = ((Digital)product).getMemory();
            if (((Digital)product).getMemory() > max)
                max = ((Digital)product).getMemory();
        }

        range.add(min);
        range.add(max);

        return range;
    }

    public ArrayList<Product> memoryFilter(int min, int max) {
        ArrayList<Product> filter = new ArrayList<>();

        for (Product product : getProducts())
            if (memoryRange().get(0) <= ((Digital)product).getMemory() &&
                    ((Digital)product).getMemory() <= memoryRange().get(1))
                if (min <= ((Digital)product).getMemory() && ((Digital)product).getMemory() <= max)
                    filter.add(product);

        return filter;
    }

    public ArrayList<Integer> ramRange() {
        int min = ((Digital)(getProducts().get(0))).getRam();
        int max = ((Digital)(getProducts().get(0))).getRam();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Digital) product).getRam() < min)
                min = ((Digital)product).getRam();
            if (((Digital)product).getRam() > max)
                max = ((Digital)product).getRam();
        }

        range.add(min);
        range.add(max);

        return range;
    }

    public ArrayList<Product> ramFilter(int min, int max) {
        ArrayList<Product> filter = new ArrayList<>();

        for (Product product : getProducts())
            if (ramRange().get(0) <= ((Digital)product).getRam() &&
                    ((Digital)product).getRam() <= ramRange().get(1))
                if (min <= ((Digital)product).getRam() && ((Digital)product).getRam() <= max)
                    filter.add(product);

        return filter;
    }

    public ArrayList<String> osRange() {
        ArrayList<String> range = new ArrayList<>();
        for (Product product : getProducts()) {
            //if (product.isAccepted()) {
                boolean add = false;
                for (String s : range)
                    if (((Digital) product).getOS().equals(s)) {
                        add = true;
                        break;
                    }
                if (!add)
                    range.add(((Digital) product).getOS());
            }
        //}
        return range;
    }

    public ArrayList<Product> osFilter(ArrayList<String> os) {
        ArrayList<Product> filter = new ArrayList<>();
        for (Product product : getProducts())
            for (String s : os)
                if (((Digital) product).getOS().equals(s)) {
                    filter.add(product);
                    break;
                }
        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", memory" +
                ", ram" +
                ", os";
    }
}
