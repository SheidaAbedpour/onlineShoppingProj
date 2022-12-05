package guiProducts;

import Filter.LaptopFilter;
import products.Product;
import products.ProductKind;
import users.Admin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LaptopFilterGUI extends JFrame {
    private JPanel Panel;
    private JLabel priceRange;
    private JSlider sliderPrice;
    private JLabel InventoryBtn;
    private JRadioButton radioButtonInventory;
    private JLabel labelRank;
    private JSlider sliderRank;
    private JLabel ramLabel;
    private JSlider sliderRam;
    private JLabel memoryLabel;
    private JSlider sliderMemory;
    private JLabel osLabel;
    private JScrollPane scrollOS;
    private JList<String> listOS;
    private JLabel cpuLabel;
    private JSlider sliderCPU;
    private JLabel gamingLabel;
    private JButton butOk;
    private JRadioButton radioButtonGaming;
    private JPanel Panel1;
    private JList<String> listCPU;
    private ArrayList<Product> laptops = new ArrayList<>();
    private LaptopFilter laptopFilter;

    public LaptopFilterGUI(LaptopShow laptopShow) {

        setLaptops();
        laptopFilter = new LaptopFilter(laptops);
        setFields();

        setContentPane(Panel1);
        setVisible(true);
        pack();


        butOk.addActionListener(e -> {

            List<ArrayList<Product>> filter = new ArrayList<>();

            if (sliderPrice.getValue() != sliderPrice.getMinimum()) {
                ArrayList<Product> price = laptopFilter.priceFilter(sliderPrice.getMinimum(), sliderPrice.getValue());
                filter.add(price);
            }

            ArrayList<Product> inventory;
            if (radioButtonInventory.isSelected()) {
                inventory = laptopFilter.inventoryFilter();
                filter.add(inventory);
            }

            if (sliderRank.getValue() != sliderRank.getMinimum()) {
                ArrayList<Product> rank = laptopFilter.rankFilter(sliderRank.getMinimum(), sliderRank.getValue());
                filter.add(rank);
            }

            if (sliderRam.getValue() != sliderRam.getMinimum()) {
                ArrayList<Product> ram = laptopFilter.rankFilter(sliderRam.getMinimum(), sliderRam.getValue());
                filter.add(ram);
            }


            if (sliderMemory.getValue() != sliderMemory.getMinimum()) {
                ArrayList<Product> memory = laptopFilter.memoryFilter(sliderMemory.getMinimum(),sliderMemory.getValue());
                filter.add(memory);
            }

            if (!listCPU.isSelectionEmpty()) {
                ArrayList<String> cpus = (ArrayList<String>) listCPU.getSelectedValuesList();
                ArrayList<Product> cpu = laptopFilter.cpuFilter(cpus);
                filter.add(cpu);
            }


            ArrayList<Product> gaming;
            if (radioButtonGaming.isSelected()) {
                gaming = laptopFilter.gamingFilter();
                filter.add(gaming);
            }


            if (!listOS.isSelectionEmpty()) {
                ArrayList<String> oss = (ArrayList<String>) listOS.getSelectedValuesList();
                ArrayList<Product> os = laptopFilter.osFilter(oss);
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
                laptopShow.setListLaptop(result);

            dispose();

        });


    }

    private void setLaptops() {
        for (Product product : Admin.getAdmin().getProducts())
            if (product.getProductKind().equals(ProductKind.LAPTOP))
                laptops.add(product);
    }

    private void setFields() {

        double minPrice = laptopFilter.priceRange().get(0);
        double maxPrice = laptopFilter.priceRange().get(1);
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

        int minRAM = laptopFilter.ramRange().get(0);
        int maxRAM = laptopFilter.ramRange().get(1);
        sliderRam.setOpaque(true);
        sliderRam.setPaintTrack(true);
        sliderRam.setPaintTicks(true);
        sliderRam.setPaintLabels(true);
        sliderRam.setMinimum(minRAM);
        sliderRam.setMaximum(maxRAM);
        sliderRam.setMinorTickSpacing(1);
        sliderRam.setMajorTickSpacing(1);


        int minMemory = laptopFilter.memoryRange().get(0);
        int maxMemory = laptopFilter.memoryRange().get(1);
        sliderMemory.setOpaque(true);
        sliderMemory.setPaintTrack(true);
        sliderMemory.setPaintTicks(true);
        sliderMemory.setPaintLabels(true);
        sliderMemory.setMinimum(minMemory);
        sliderMemory.setMaximum(maxMemory);
        sliderMemory.setMinorTickSpacing(1);
        sliderMemory.setMajorTickSpacing(1);


        DefaultListModel<String> listOS2 = new DefaultListModel<>();
        ArrayList<String> osRange = laptopFilter.osRange();
        for (String s : osRange) {
            listOS2.addElement(s);
        }
        listOS.setModel(listOS2);


        DefaultListModel<String> listCpu1 = new DefaultListModel<>();
        ArrayList<String> cpuRange = laptopFilter.cpuRange();
        for (String s : cpuRange) {
            listCpu1.addElement(s);
        }
        listCPU.setModel(listCpu1);

    }

}
