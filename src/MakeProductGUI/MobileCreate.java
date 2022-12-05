package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.*;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MobileCreate extends JFrame {

    private Mobile mobile;
    private JPanel panel1;
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
    private JTextField textSim;
    private JTextField textCamera;
    private JButton butOK;

    public MobileCreate(RequestFunction requestFunction, Mobile mobile1,Seller seller) {

        setContentPane(panel1);
        setVisible(true);
        pack();

        if (mobile1 != null) {
            textID.setText(mobile1.getID());
            textName.setText(mobile1.getName());
            textBrand.setText(mobile1.getBrand());
            textPrice.setText(String.valueOf(mobile1.getPrice()));
            textNumber.setText(String.valueOf(mobile1.getNumber()));
            textDescription.setText(mobile1.getDescription());
            textMemory.setText(String.valueOf(mobile1.getMemory()));
            textRAM.setText(String.valueOf(mobile1.getRam()));
            textOS.setText(mobile1.getOS());
            textWeight.setText(String.valueOf(mobile1.getWeight()));
            textSize.setText(mobile1.getSize());
            textSim.setText(String.valueOf(mobile1.getSimCards()));
            textCamera.setText(String.valueOf(mobile1.getCameraQuality()));
        }

        butOK.addActionListener(e -> {

            try {
                mobile = new Mobile(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),Integer.parseInt(textMemory.getText()),
                        Integer.parseInt(textRAM.getText()),textOS.getText(), Double.parseDouble(textWeight.getText()),
                        textSize.getText(),Integer.parseInt(textSim.getText()),Integer.parseInt(textCamera.getText()));
                setRequest(requestFunction,mobile1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);

        });


    }

    public void setRequest(RequestFunction requestFunction, Product product1,Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getMobile();
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

    public Mobile getMobile() {
        return mobile;
    }

}
