package guiProducts;

import Filter.FoodFilter;
import products.Product;
import products.ProductKind;
import users.Admin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodFilterGUI extends JFrame {


    private JPanel Panel1;
    private JPanel Panel;
    private JLabel priceRange;
    private JSlider sliderPrice;
    private JLabel InventoryBtn;
    private JRadioButton radioButtonInventory;
    private JLabel labelRank;
    private JSlider sliderRank;
    private JButton butOk;
    private JPanel Panel2;
    private ArrayList<Product> foods = new ArrayList<>();
    private FoodFilter foodFilter;

    public FoodFilterGUI(FoodShowGUI foodShowGUI) {

        setFoods();
        foodFilter = new FoodFilter(foods);
        setFields();

        setContentPane(Panel1);
        setVisible(true);
        pack();


        butOk.addActionListener(e -> {

            List<ArrayList<Product>> filter = new ArrayList<>();

            if (sliderPrice.getValue() != sliderPrice.getMinimum()) {
                ArrayList<Product> price = foodFilter.priceFilter(sliderPrice.getMinimum(), sliderPrice.getValue());
                filter.add(price);
            }

            ArrayList<Product> inventory;
            if (radioButtonInventory.isSelected()) {
                inventory = foodFilter.inventoryFilter();
                filter.add(inventory);
            }

            if (sliderRank.getValue() != sliderRank.getMinimum()) {
                ArrayList<Product> rank = foodFilter.rankFilter(sliderRank.getMinimum(), sliderRank.getValue());
                filter.add(rank);
            }



            ArrayList<Product> result = new ArrayList<>();
            int i = 0;
            for (int j = 0; j < filter.get(i).size(); j++) {
                boolean repeat = false;
                for (int ii = i + 1; ii < filter.size(); ii++) {
                    repeat = false;
                    for (int jj = 0; jj < filter.get(ii).size(); jj++) {
                        if (Objects.equals(filter.get(ii).get(jj).getID(), filter.get(i).get(j).getID())) {
                            repeat = true;
                            break;
                        }
                    }
                }
                if (repeat)
                    result.add(filter.get(i).get(j));
            }

            if (result.size() != 0)
                foodShowGUI.setListFood(result);

            dispose();

        });


    }

    private void setFoods() {
        for (Product product : Admin.getAdmin().getProducts())
            if (product.getProductKind().equals(ProductKind.FOOD))
                foods.add(product);
    }

    private void setFields() {

        double minPrice = foodFilter.priceRange().get(0);
        double maxPrice = foodFilter.priceRange().get(1);
        sliderPrice.setOpaque(true);
        sliderPrice.setPaintTrack(true);
        sliderPrice.setPaintTicks(true);
        sliderPrice.setPaintLabels(true);
        sliderPrice.setMinimum((int) minPrice);
        sliderPrice.setMaximum((int) maxPrice);
        sliderPrice.setMinorTickSpacing(50);
        sliderPrice.setMajorTickSpacing(100);

        sliderRank.setOpaque(true);
        sliderRank.setPaintTrack(true);
        sliderRank.setPaintTicks(true);
        sliderRank.setPaintLabels(true);
        sliderRank.setMinimum(0);
        sliderRank.setMaximum(5);
        sliderRank.setMinorTickSpacing(1);
        sliderRank.setMajorTickSpacing(1);


    }

}
