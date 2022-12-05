package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.Mobile;
import products.Product;
import products.Stove;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoveCreate extends JFrame {

    private Stove stove;
    private JTextField textID;
    private JTextField textName;
    private JTextField textBrand;
    private JTextField textPrice;
    private JTextField textNumber;
    private JTextField textDescription;
    private JTextField textEnergy;
    private JTextField textFlames;
    private JButton OKButton;
    private JRadioButton radioGuarantee;
    private JTextField textKind;
    private JRadioButton radioFer;
    private JPanel Panel1;

    public StoveCreate(RequestFunction requestFunction, Stove stove1, Seller seller) {

        setContentPane(Panel1);
        setVisible(true);
        pack();

        if (stove1 != null) {
            textID.setText(stove1.getID());
            textName.setText(stove1.getName());
            textBrand.setText(stove1.getBrand());
            textPrice.setText(String.valueOf(stove1.getPrice()));
            textNumber.setText(String.valueOf(stove1.getNumber()));
            textDescription.setText(stove1.getDescription());
            textEnergy.setText(stove1.getEnergyUsage());
            radioGuarantee.setSelected(stove1.isGuaranteed());
            textFlames.setText(String.valueOf(stove1.getFlamesNumber()));
            textKind.setText(stove1.getKind());
            radioFer.setSelected(stove1.isHasFer());
        }

        OKButton.addActionListener(e -> {

            try {
                stove = new Stove(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),textEnergy.getText(),radioGuarantee.isSelected(),
                        Integer.parseInt(textFlames.getText()),textKind.getText(),radioFer.isSelected());
                setRequest(requestFunction,stove1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);
        });

    }

    public void setRequest(RequestFunction requestFunction, Product product1, Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getStove();
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

    public Stove getStove() {
        return stove;
    }

}