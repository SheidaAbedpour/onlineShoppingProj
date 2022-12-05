package guiProducts;

import Filter.MobileFilter;
import products.Product;
import products.ProductKind;
import users.Admin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MobileFilterGUI extends JFrame {
    private JSlider sliderPrice;
    private JLabel priceRange;
    private JPanel Panel;
    private JLabel InventoryBtn;
    private JRadioButton radioButtonInventory;
    private JSlider sliderRank;
    private JLabel labelRank;
    private JSlider sliderRam;
    private JLabel ramLabel;
    private JSlider sliderMemory;
    private JLabel memoryLabel;
    private JLabel osLabel;
    private JSlider sliderSim;
    private JSlider sliderCamera;
    private JLabel simLabel;
    private JLabel cameraLabel;
    private JButton butOk;
    private JList<String> listOS;
    private JScrollPane scrollOS;
    private ArrayList<Product> mobiles = new ArrayList<>();
    MobileFilter mobileFilter;

    public MobileFilterGUI(MobileShow mobileShow) {

        setMobiles();
        mobileFilter = new MobileFilter(mobiles);
        setFields();

        setVisible(true);
        setContentPane(Panel);
        pack();

        List<ArrayList<Product>> filter = new ArrayList<>();

        butOk.addActionListener(e -> {

            if (sliderPrice.getValue() != sliderPrice.getMinimum()) {
                ArrayList<Product> price = mobileFilter.priceFilter(sliderPrice.getMinimum(), sliderPrice.getValue());
                filter.add(price);
            }

            ArrayList<Product> inventory;
            if (radioButtonInventory.isSelected()) {
                inventory = mobileFilter.inventoryFilter();
                filter.add(inventory);
            }

            if (sliderRank.getValue() != sliderRank.getMinimum()) {
                ArrayList<Product> rank = mobileFilter.rankFilter(sliderRank.getMinimum(), sliderRank.getValue());
                filter.add(rank);
            }

            if (sliderRam.getValue() != sliderRam.getMinimum()) {
                ArrayList<Product> ram = mobileFilter.rankFilter(sliderRam.getMinimum(), sliderRam.getValue());
                filter.add(ram);
            }


            if (sliderMemory.getValue() != sliderMemory.getMinimum()) {
                ArrayList<Product> memory = mobileFilter.memoryFilter(sliderMemory.getMinimum(),sliderMemory.getValue());
                filter.add(memory);
            }

            if (sliderSim.getValue() != sliderSim.getMinimum()) {
                ArrayList<Product> sim = mobileFilter.simCardsFilter(sliderSim.getMinimum(), sliderSim.getValue());
                filter.add(sim);
            }


            if (sliderCamera.getValue() != sliderCamera.getMinimum()) {
                ArrayList<Product> camera = mobileFilter.cameraQualityFilter(sliderCamera.getMinimum(),sliderCamera.getValue());
                filter.add(camera);
            }


            if (!listOS.isSelectionEmpty()) {
                ArrayList<String> oss = (ArrayList<String>) listOS.getSelectedValuesList();
                ArrayList<Product> os = mobileFilter.osFilter(oss);
                filter.add(os);
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
                mobileShow.setMobiles(result);

            dispose();

        });
    }

    private void setMobiles() {
        for (Product product : Admin.getAdmin().getProducts())
            if (product.getProductKind().equals(ProductKind.MOBILE))
                mobiles.add(product);
    }

    private void setFields() {

        double minPrice = mobileFilter.priceRange().get(0);
        double maxPrice = mobileFilter.priceRange().get(1);
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

        int minRAM = mobileFilter.ramRange().get(0);
        int maxRAM = mobileFilter.ramRange().get(1);
        sliderRam.setOpaque(true);
        sliderRam.setPaintTrack(true);
        sliderRam.setPaintTicks(true);
        sliderRam.setPaintLabels(true);
        sliderRam.setMinimum(minRAM);
        sliderRam.setMaximum(maxRAM);
        sliderRam.setMinorTickSpacing(1);
        sliderRam.setMajorTickSpacing(1);


        int minMemory = mobileFilter.memoryRange().get(0);
        int maxMemory = mobileFilter.memoryRange().get(1);
        sliderMemory.setOpaque(true);
        sliderMemory.setPaintTrack(true);
        sliderMemory.setPaintTicks(true);
        sliderMemory.setPaintLabels(true);
        sliderMemory.setMinimum(minMemory);
        sliderMemory.setMaximum(maxMemory);
        sliderMemory.setMinorTickSpacing(1);
        sliderMemory.setMajorTickSpacing(1);


        int minSim = mobileFilter.simRange().get(0);
        int maxSim = mobileFilter.simRange().get(1);
        sliderSim.setOpaque(true);
        sliderSim.setPaintTrack(true);
        sliderSim.setPaintTicks(true);
        sliderSim.setPaintLabels(true);
        sliderSim.setMinimum(minSim);
        sliderSim.setMaximum(maxSim);
        sliderSim.setMinorTickSpacing(1);
        sliderSim.setMajorTickSpacing(1);


        int minCamera = mobileFilter.cameraQualityRange().get(0);
        int maxCamera = mobileFilter.cameraQualityRange().get(1);
        sliderCamera.setOpaque(true);
        sliderCamera.setPaintTrack(true);
        sliderCamera.setPaintTicks(true);
        sliderCamera.setPaintLabels(true);
        sliderCamera.setMinimum(minCamera);
        sliderCamera.setMaximum(maxCamera);
        sliderCamera.setMinorTickSpacing(1);
        sliderCamera.setMajorTickSpacing(1);


        DefaultListModel<String> listOS2 = new DefaultListModel<>();
        ArrayList<String> osRange = mobileFilter.osRange();
        for (String s : osRange) {
            listOS2.addElement(s);
        }
        listOS.setModel(listOS2);

    }

}
