package guiProducts;

import Filter.RefrigeratorFilter;
import products.Product;
import products.ProductKind;
import users.Admin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RefrigeratorFilterGUI extends JFrame {


    private JPanel Panel;
    private JLabel priceRange;
    private JSlider sliderPrice;
    private JLabel InventoryBtn;
    private JRadioButton radioButtonInventory;
    private JLabel labelRank;
    private JSlider sliderRank;
    private JButton butOk;
    private JLabel energyLabel;
    private JList<String> listEnergy;
    private JRadioButton radioButtonGaurantee;
    private JPanel Panel1;
    private JList<String> listKind;
    private JRadioButton radioFreezer;
    private ArrayList<Product> refrigerators = new ArrayList<>();
    private RefrigeratorFilter refrigeratorFilter;

    public RefrigeratorFilterGUI(RefrigeratorShow refrigeratorShow) {

        setRefrigerators();
        refrigeratorFilter = new RefrigeratorFilter(refrigerators);
        setFields();

        setContentPane(Panel1);
        setVisible(true);
        pack();

        butOk.addActionListener(e -> {

            List<ArrayList<Product>> filter = new ArrayList<>();

            if (sliderPrice.getValue() != sliderPrice.getMinimum()) {
                ArrayList<Product> price = refrigeratorFilter.priceFilter(sliderPrice.getMinimum(), sliderPrice.getValue());
                filter.add(price);
            }

            ArrayList<Product> inventory;
            if (radioButtonInventory.isSelected()) {
                inventory = refrigeratorFilter.inventoryFilter();
                filter.add(inventory);
            }

            if (sliderRank.getValue() != sliderRank.getMinimum()) {
                ArrayList<Product> rank = refrigeratorFilter.rankFilter(sliderRank.getMinimum(), sliderRank.getValue());
                filter.add(rank);
            }

            if (!listEnergy.isSelectionEmpty()) {
                ArrayList<String> energies = (ArrayList<String>) listEnergy.getSelectedValuesList();
                ArrayList<Product> energy = refrigeratorFilter.energyUsageFilter(energies);
                filter.add(energy);
            }

            if (!listKind.isSelectionEmpty()) {
                ArrayList<String> kinds = (ArrayList<String>) listKind.getSelectedValuesList();
                ArrayList<Product> kind = refrigeratorFilter.kindFilter(kinds);
                filter.add(kind);
            }

            ArrayList<Product> guarantee;
            if (radioButtonGaurantee.isSelected()) {
                guarantee = refrigeratorFilter.guaranteeFilter();
                filter.add(guarantee);
            }


            ArrayList<Product> freezer;
            if (radioFreezer.isSelected()) {
                freezer = refrigeratorFilter.freezerFilter();
                filter.add(freezer);
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
                refrigeratorShow.setListRefrigerator(result);

            dispose();

        });


    }

    private void setRefrigerators() {
        for (Product product : Admin.getAdmin().getProducts())
            if (product.getProductKind().equals(ProductKind.REFRIGERATOR))
                refrigerators.add(product);
    }

    private void setFields() {

        double minPrice = refrigeratorFilter.priceRange().get(0);
        double maxPrice = refrigeratorFilter.priceRange().get(1);
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


        DefaultListModel<String> list = new DefaultListModel<>();
        ArrayList<String> energyRange = refrigeratorFilter.energyRange();
        for (String s : energyRange) {
            list.addElement(s);
        }
        listEnergy.setModel(list);


        DefaultListModel<String> list2 = new DefaultListModel<>();
        ArrayList<String> kindRange = refrigeratorFilter.kindRange();
        for (String s : kindRange) {
            list2.addElement(s);
        }
        listKind.setModel(list2);

    }

}
