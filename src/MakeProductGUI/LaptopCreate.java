package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.Laptop;
import products.Mobile;
import products.Product;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LaptopCreate extends JFrame {

    private Laptop laptop;
    private JTextField textID;
    private JTextField textName;
    private JTextField textBrand;
    private JTextField textPrice;
    private JTextField textNumber;
    private JTextField textDescription;
    private JTextField textMemory;
    private JTextField textRAM;
    private JTextField textOS;
    private JTextField textWeight;
    private JTextField textSize;
    private JTextField textCPU;
    private JRadioButton radioGaming;
    private JButton OKButton;
    private JPanel Panel1;

    public LaptopCreate(RequestFunction requestFunction, Laptop laptop1, Seller seller) {

        setContentPane(Panel1);
        setVisible(true);
        pack();

        if (laptop1 != null) {
            textID.setText(laptop1.getID());
            textName.setText(laptop1.getName());
            textBrand.setText(laptop1.getBrand());
            textPrice.setText(String.valueOf(laptop1.getPrice()));
            textNumber.setText(String.valueOf(laptop1.getNumber()));
            textDescription.setText(laptop1.getDescription());
            textMemory.setText(String.valueOf(laptop1.getMemory()));
            textRAM.setText(String.valueOf(laptop1.getRam()));
            textOS.setText(laptop1.getOS());
            textWeight.setText(String.valueOf(laptop1.getWeight()));
            textSize.setText(laptop1.getSize());
            textCPU.setText(laptop1.getCPU());
            radioGaming.setSelected(laptop1.isGaming());
        }

        OKButton.addActionListener(e -> {

            try {
                laptop = new Laptop(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),Integer.parseInt(textMemory.getText()),
                        Integer.parseInt(textRAM.getText()),textOS.getText(), Double.parseDouble(textWeight.getText()),
                        textSize.getText(),textCPU.getText(),radioGaming.isSelected());
                setRequest(requestFunction,laptop1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);
        });

    }

    public void setRequest(RequestFunction requestFunction, Product product1, Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getLaptop();
        ProductRequest productRequest;

        if (product2 != null) {
            if (requestFunction.equals(RequestFunction.EDIT))
                products.add(product1);
            products.add(product2);
            productRequest = new ProductRequest(seller, products, requestFunction);
            if (requestFunction.equals(RequestFunction.ADD)) {
                Admin.getAdmin().setProductRequestsAdd(productRequest);
            }
            else if (requestFunction.equals(RequestFunction.EDIT)){
                Admin.getAdmin().setProductRequestEdit(productRequest);
            }
            JOptionPane.showInputDialog("Done");
        }
    }


    public Laptop getLaptop() {
        return laptop;
    }
}
