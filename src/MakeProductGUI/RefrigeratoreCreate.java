package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.Mobile;
import products.Product;
import products.Refrigerator;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RefrigeratoreCreate extends JFrame{

    private Refrigerator refrigerator;
    private JTextField textID;
    private JTextField textName;
    private JTextField textBrand;
    private JTextField textPrice;
    private JTextField textNumber;
    private JTextField textDescription;
    private JTextField textEnergy;
    private JTextField textCapacity;
    private JButton OKButton;
    private JRadioButton radioGuarantee;
    private JTextField textKind;
    private JRadioButton radioFreezer;
    private JPanel Panel1;

    public RefrigeratoreCreate(RequestFunction requestFunction, Refrigerator refrigerator1, Seller seller) {

        setContentPane(Panel1);
        setVisible(true);
        pack();


        if (refrigerator1 != null) {
            textID.setText(refrigerator1.getID());
            textName.setText(refrigerator1.getName());
            textBrand.setText(refrigerator1.getBrand());
            textPrice.setText(String.valueOf(refrigerator1.getPrice()));
            textNumber.setText(String.valueOf(refrigerator1.getNumber()));
            textDescription.setText(refrigerator1.getDescription());
            textEnergy.setText(refrigerator1.getEnergyUsage());
            radioGuarantee.setSelected(refrigerator1.isGuaranteed());
            textCapacity.setText(String.valueOf(refrigerator1.getCapacity()));
            textKind.setText(refrigerator1.getKind());
            radioFreezer.setSelected(refrigerator1.isHasFreezer());
        }

        OKButton.addActionListener(e -> {

            try {
                refrigerator = new Refrigerator(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),textEnergy.getText(),radioGuarantee.isSelected(),
                        Integer.parseInt(textCapacity.getText()),textKind.getText(),radioFreezer.isSelected());
                setRequest(requestFunction,refrigerator1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);
        });

    }

    public void setRequest(RequestFunction requestFunction, Product product1, Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getRefrigerator();
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

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }
}
