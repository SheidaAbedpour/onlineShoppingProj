package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.Mobile;
import products.Product;
import products.Shoes;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.util.ArrayList;

public class ShoesCreate extends JFrame {

    private Shoes shoes;
    private JTextField textID;
    private JTextField textName;
    private JTextField textBrand;
    private JTextField textPrice;
    private JTextField textNumber;
    private JTextField textDescription;
    private JTextField textCountry;
    private JTextField textMaterial;
    private JTextField textSize;
    private JButton OKButton;
    private JComboBox<String> comboKind;
    private JPanel Panel1;


    public ShoesCreate(RequestFunction requestFunction, Shoes shoes1, Seller seller) {

        setContentPane(Panel1);
        setVisible(true);
        pack();

        if (shoes1 != null) {
            textID.setText(shoes1.getID());
            textName.setText(shoes1.getName());
            textBrand.setText(shoes1.getBrand());
            textPrice.setText(String.valueOf(shoes1.getPrice()));
            textNumber.setText(String.valueOf(shoes1.getNumber()));
            textDescription.setText(shoes1.getDescription());
            textCountry.setText(shoes1.getCountry());
            textMaterial.setText(shoes1.getMaterial());
            textSize.setText(String.valueOf(shoes1.getSize()));
            comboKind.setSelectedItem(String.valueOf(shoes1.getKind()));
        }

        String[] kinds = {"Sandal", "Boot", "Sneaker"};
        for (String s : kinds)
            comboKind.addItem(s);


        OKButton.addActionListener(e -> {

            try {
                shoes = new Shoes(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),textCountry.getText(),textMaterial.getText(),
                        Integer.parseInt(textSize.getText()),(String) comboKind.getSelectedItem());
                setRequest(requestFunction,shoes1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);

        });

    }

    public void setRequest(RequestFunction requestFunction, Product product1, Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getShoes();
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

    public Shoes getShoes() {
        return shoes;
    }
}
