package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.Mobile;
import products.Product;
import products.Television;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TVCreate extends JFrame{

    private Television tv;
    private JTextField textID;
    private JTextField textName;
    private JTextField textBrand;
    private JTextField textPrice;
    private JTextField textNumber;
    private JTextField textDescription;
    private JTextField textEnergy;
    private JTextField textQuality;
    private JButton OKButton;
    private JRadioButton radioGuarantee;
    private JTextField textSize;
    private JPanel Panel1;

    public TVCreate(RequestFunction requestFunction, Television tv1, Seller seller) {

        setContentPane(Panel1);
        setVisible(true);
        pack();

        if (tv1 != null){
            textID.setText(tv1.getID());
            textName.setText(tv1.getName());
            textBrand.setText(tv1.getBrand());
            textPrice.setText(String.valueOf(tv1.getPrice()));
            textNumber.setText(String.valueOf(tv1.getNumber()));
            textDescription.setText(tv1.getDescription());
            textEnergy.setText(tv1.getEnergyUsage());
            radioGuarantee.setSelected(tv1.isGuaranteed());
            textQuality.setText(String.valueOf(tv1.getQuality()));
            textSize.setText(tv1.getSize());
        }

        OKButton.addActionListener(e -> {

            try {
                tv = new Television(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),textEnergy.getText(),radioGuarantee.isSelected(),
                        Integer.parseInt(textQuality.getText()),textSize.getText());
                setRequest(requestFunction,tv1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);
        });

    }

    public void setRequest(RequestFunction requestFunction, Product product1, Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getTv();
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


    public Television getTv() {
        return tv;
    }
}
