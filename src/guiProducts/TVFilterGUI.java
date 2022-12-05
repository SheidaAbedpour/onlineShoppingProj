package guiProducts;

import Filter.TelevisionFilter;
import products.Product;
import products.ProductKind;
import users.Admin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TVFilterGUI extends JFrame {
    private JPanel Panel;
    private JLabel priceRange;
    private JSlider sliderPrice;
    private JLabel InventoryBtn;
    private JRadioButton radioButtonInventory;
    private JLabel labelRank;
    private JSlider sliderRank;
    private JButton butOk;
    private JPanel Panel1;
    private JList<String> listEnergy;
    private JLabel energyLabel;
    private JRadioButton radioButtonGaurantee;
    private JSlider sliderQuality;
    private ArrayList<Product> tvs = new ArrayList<>();
    private TelevisionFilter televisionFilter;


    public TVFilterGUI(TVshow tVshow) {

        setTvs();
        televisionFilter = new TelevisionFilter(tvs);
        setFields();

        setVisible(true);
        setContentPane(Panel1);
        pack();


        butOk.addActionListener(e -> {

            List<ArrayList<Product>> filter = new ArrayList<>();

            if (sliderPrice.getValue() != sliderPrice.getMinimum()) {
                    ArrayList<Product> price = televisionFilter.priceFilter(sliderPrice.getMinimum(), sliderPrice.getValue());
                    filter.add(price);
                }

            ArrayList<Product> inventory;
            if (radioButtonInventory.isSelected()) {
                    inventory = televisionFilter.inventoryFilter();
                    filter.add(inventory);
                }

            if (sliderRank.getValue() != sliderRank.getMinimum()) {
                    ArrayList<Product> rank = televisionFilter.rankFilter(sliderRank.getMinimum(), sliderRank.getValue());
                    filter.add(rank);
                }

            if (!listEnergy.isSelectionEmpty()) {
                    ArrayList<String> energies = (ArrayList<String>) listEnergy.getSelectedValuesList();
                    ArrayList<Product> energy = televisionFilter.energyUsageFilter(energies);
                    filter.add(energy);
                }

            ArrayList<Product> guarantee;
            if (radioButtonGaurantee.isSelected()) {
                guarantee = televisionFilter.guaranteeFilter();
                filter.add(guarantee);
            }

            if (sliderQuality.getValue() != sliderQuality.getMinimum()) {
                ArrayList<Product> quality = televisionFilter.qualityFilter(sliderQuality.getMinimum(),sliderQuality.getValue());
                filter.add(quality);
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
                tVshow.setTV(result);

            dispose();

        });

    }

    public void setTvs() {
        for (Product product : Admin.getAdmin().getProducts())
            if (product.getProductKind().equals(ProductKind.TELEVISION))
                tvs.add(product);
    }

    private void setFields() {

        double minPrice = televisionFilter.priceRange().get(0);
        double maxPrice = televisionFilter.priceRange().get(1);
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
        ArrayList<String> energyRange = televisionFilter.energyRange();
        for (String s : energyRange) {
            list.addElement(s);
        }
        listEnergy.setModel(list);



        int minQ = televisionFilter.qualityRange().get(0);
        int maxQ = televisionFilter.qualityRange().get(1);
        sliderQuality.setOpaque(true);
        sliderQuality.setPaintTrack(true);
        sliderQuality.setPaintTicks(true);
        sliderQuality.setPaintLabels(true);
        sliderQuality.setMinimum(minQ);
        sliderQuality.setMaximum(maxQ);
        sliderPrice.setMinorTickSpacing(1);
        sliderPrice.setMajorTickSpacing(1);

    }

}
