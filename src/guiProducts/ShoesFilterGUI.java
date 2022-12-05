package guiProducts;

import Filter.ShoesFilter;
import products.Product;
import products.ProductKind;
import users.Admin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoesFilterGUI extends JFrame {
    private JPanel Panel;
    private JLabel priceRange;
    private JSlider sliderPrice;
    private JLabel InventoryBtn;
    private JRadioButton radioButtonInventory;
    private JLabel labelRank;
    private JSlider sliderRank;
    private JButton butOk;
    private JList<String> listCountry;
    private JList<String> listMaterial;
    private JList<Integer> listSize;
    private JList<String> listKind;
    private JPanel Panel1;
    private ArrayList<Product> shoes = new ArrayList<>();
    private final ShoesFilter shoesFilter;


    public ShoesFilterGUI(ShoesShow shoesShow) {

        setShoes();
        shoesFilter = new ShoesFilter(shoes);
        setFields();

        setContentPane(Panel1);
        setVisible(true);
        pack();

        butOk.addActionListener(e -> {
            List<ArrayList<Product>> filter = new ArrayList<>();

            if (sliderPrice.getValue() != sliderPrice.getMinimum()) {
                ArrayList<Product> price = shoesFilter.priceFilter(sliderPrice.getMinimum(), sliderPrice.getValue());
                filter.add(price);
            }

            ArrayList<Product> inventory;
            if (radioButtonInventory.isSelected()) {
                inventory = shoesFilter.inventoryFilter();
                filter.add(inventory);
            }

            if (sliderRank.getValue() != sliderRank.getMinimum()) {
                ArrayList<Product> rank = shoesFilter.rankFilter(sliderRank.getMinimum(), sliderRank.getValue());
                filter.add(rank);
            }



            if (!listCountry.isSelectionEmpty()) {
                ArrayList<String> countries = (ArrayList<String>) listCountry.getSelectedValuesList();
                ArrayList<Product> country = shoesFilter.countryFilter(countries);
                filter.add(country);
            }

            if (!listMaterial.isSelectionEmpty()) {
                ArrayList<String> materials = (ArrayList<String>) listMaterial.getSelectedValuesList();
                ArrayList<Product> material = shoesFilter.materialFilter(materials);
                filter.add(material);
            }

            if (!listSize.isSelectionEmpty()) {
                ArrayList<Integer> sizes = (ArrayList<Integer>) listSize.getSelectedValuesList();
                ArrayList<Product> size = shoesFilter.sizeFilter(sizes);
                filter.add(size);
            }

            if (!listKind.isSelectionEmpty()) {
                ArrayList<String> kinds = (ArrayList<String>) listKind.getSelectedValuesList();
                ArrayList<Product> kind = shoesFilter.kindFilter(kinds);
                filter.add(kind);
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
                shoesShow.setShoes(result);

            dispose();
        });
    }

    private void setShoes() {
        for (Product product : Admin.getAdmin().getProducts())
            if (product.getProductKind().equals(ProductKind.SHOES))
                shoes.add(product);
    }

    private void setFields() {

        double minPrice = shoesFilter.priceRange().get(0);
        double maxPrice = shoesFilter.priceRange().get(1);
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


        DefaultListModel<String> listCountry1 = new DefaultListModel<>();
        ArrayList<String> countryRange = shoesFilter.countryRange();
        for (String s : countryRange) {
            listCountry1.addElement(s);
        }
        listCountry.setModel(listCountry1);


        DefaultListModel<String> listMaterial1 = new DefaultListModel<>();
        ArrayList<String> materialRang =shoesFilter.materialRange();
        for (String s : materialRang) {
            listMaterial1.addElement(s);
        }
        listMaterial.setModel(listMaterial1);


        DefaultListModel<Integer> listSize1 = new DefaultListModel<>();
        ArrayList<Integer> sizeRang = shoesFilter.sizeRange();
        for (int s : sizeRang) {
            listSize1.addElement(s);
        }
        listSize.setModel(listSize1);


        DefaultListModel<String> listKind1 = new DefaultListModel<>();
        ArrayList<String> kindRang = shoesFilter.kindRange();
        for (String s : kindRang) {
            listKind1.addElement(s);
        }
        listKind.setModel(listKind1);


    }

}
