package Filter;

import category.Category;
import products.Mobile;
import products.Product;

import java.util.ArrayList;

public class MobileFilter extends DigitalFilter {

    public MobileFilter(ArrayList<Product> products) {
        super(products);
    }

    public ArrayList<Integer> simRange() {

        int min = ((Mobile)(getProducts().get(0))).getSimCards();
        int max = ((Mobile)(getProducts().get(0))).getSimCards();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Mobile) product).getSimCards() < min)
                min = ((Mobile)product).getSimCards();
            if (((Mobile)product).getSimCards() > max)
                max = ((Mobile)product).getSimCards();
        }

        range.add(min);
        range.add(max);

        return range;

    }

    public ArrayList<Product> simCardsFilter(int min, int max) {
        ArrayList<Product> filter = new ArrayList<>();

        for (Product product : getProducts())
            if (simRange().get(0) <= ((Mobile)product).getSimCards() &&
                    ((Mobile)product).getSimCards() <= simRange().get(1))
                if (min <= ((Mobile)product).getSimCards() && ((Mobile)product).getSimCards() <= max)
                    filter.add(product);

        return filter;
    }

    public ArrayList<Integer> cameraQualityRange() {

        int min = ((Mobile)(getProducts().get(0))).getCameraQuality();
        int max = ((Mobile)(getProducts().get(0))).getCameraQuality();
        ArrayList<Integer> range = new ArrayList<>();

        for (Product product : getProducts()) {
            if (((Mobile) product).getCameraQuality() < min)
                min = ((Mobile)product).getCameraQuality();
            if (((Mobile)product).getCameraQuality() > max)
                max = ((Mobile)product).getCameraQuality();
        }

        range.add(min);
        range.add(max);

        return range;

    }

    public ArrayList<Product> cameraQualityFilter(int min, int max) {
        ArrayList<Product> filter = new ArrayList<>();

        for (Product product : getProducts())
            if (cameraQualityRange().get(0) <= ((Mobile)product).getCameraQuality() &&
                    ((Mobile)product).getCameraQuality() <= cameraQualityRange().get(1))
                if (min <= ((Mobile)product).getCameraQuality() && ((Mobile)product).getCameraQuality() <= max)
                    filter.add(product);

        return filter;
    }

    public String toString1() {
        return super.toString1() +
                ", simCards" +
                ", cameraQuality" +
                '}';
    }
}
