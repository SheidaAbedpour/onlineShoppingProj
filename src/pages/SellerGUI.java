package pages;

import MakeProductGUI.*;
import exceptions.MyExceptions;
import factor.SellingFactor;
import guiProducts.MainPageShow;
import products.*;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SellerGUI extends JFrame {

    private Seller seller;
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAcoount;
    private JTextField searchField;
    private JTextField textFirtName;
    private JTextField textLastName;
    private JTextField textEmail;
    private JTextField textPhone;
    private JLabel userNameLabel;
    private JTextField textCredit;
    private JTextField textField1;
    private JTextField textField2;
    private JList<SellingFactor> listFactor;
    private JButton butOK;
    private JPanel Panel1;
    private JTextField textCompany;
    private JList<Product> listProducts;
    private JButton butRemove;
    private JButton butEdit;
    private JButton butAdd;
    private JComboBox<String> comboAdd;


    public SellerGUI(Seller seller) {

        this.seller = seller;
        seller.setSellerGUI(this);
        setFields();
        setProducts();

        setContentPane(Panel1);
        setVisible(true);
        pack();

        String[] kinds = {"Mobile", "Laptop", "Clothes", "Shoes", "Television", "Refrigerator", "Stove", "Food"};
        for (String s : kinds)
            comboAdd.addItem(s);

        btnHome.addActionListener(e -> {
            new MainPageShow(seller);
            dispose();
        });


        butOK.addActionListener(e -> {

            seller.setFirstName(textFirtName.getText());
            seller.setLastName(textLastName.getText());
            seller.setEmail(textEmail.getText());
            seller.setPhone(textPhone.getText());
            seller.setCompany(textCompany.getText());
            seller.setCredit(Double.parseDouble(textCredit.getText()));

            if (!textField1.getText().equals("")) {
                try {
                    seller.setPassword(textField1.getText(), textField2.getText());
                } catch (MyExceptions ex) {
                    MainPage.saveExceptions(ex);
                    JOptionPane.showInputDialog(ex.getMessage());
                }
            }

            setFields();
            textField1.setText(" ");
            textField2.setText(" ");

        });


        butRemove.addActionListener(e -> {
            ArrayList<Product> product1 = new ArrayList<>();
            product1.add(listProducts.getSelectedValue());
            try {
                Admin.getAdmin().setProductRequestsRemove(new ProductRequest(seller,product1, RequestFunction.REMOVE));
            } catch (IOException ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }
        });


        butAdd.addActionListener(e -> {
            if (!seller.isAccepted())
                JOptionPane.showInputDialog("not accepted by admin");
            else
                setRequest(RequestFunction.ADD,null);
        });


        butEdit.addActionListener(e -> {
            setRequest(RequestFunction.EDIT,(Mobile) listProducts.getSelectedValue());
        });

    }


    public void setFields() {

        userNameLabel.setText(String.valueOf(seller.getID()));
        textFirtName.setText(seller.getFirstName());
        textLastName.setText(seller.getLastName());
        textEmail.setText(seller.getEmail());
        textPhone.setText(seller.getPhone());
        textCompany.setText(seller.getCompany());
        textCredit.setText(String.valueOf(seller.getCredit()));

        DefaultListModel<SellingFactor> factorDefaultListModel = new DefaultListModel<>();
        for (SellingFactor sellingFactor :seller.getSellingFactors())
            factorDefaultListModel.addElement(sellingFactor);
        listFactor.setModel(factorDefaultListModel);
    }

    public void setProducts() {
        DefaultListModel<Product> model = new DefaultListModel<>();
        for (Product product : seller.getProducts())
            model.addElement(product);
        listProducts.setModel(model);
    }

    public void setRequest(RequestFunction requestFunction, Product product1) {

        String o = (String) comboAdd.getSelectedItem();
        if ("Mobile".equals(o)) {
            MobileCreate mobileCreate = new MobileCreate(requestFunction, (Mobile) product1,seller);
        } else if ("Laptop".equals(o)) {
            LaptopCreate laptopCreate = new LaptopCreate(requestFunction, (Laptop) product1,seller);
        } else if ("Clothes".equals(o)) {
            ClothesCreate clothesCreate = new ClothesCreate(requestFunction, (Clothes) product1,seller);
        } else if ("Shoes".equals(o)) {
            ShoesCreate shoesCreate = new ShoesCreate(requestFunction, (Shoes) product1,seller);
        } else if ("Television".equals(o)) {
            TVCreate tvCreate = new TVCreate(requestFunction, (Television) product1,seller);
        } else if ("Refrigerator".equals(o)) {
            RefrigeratoreCreate refrigeratoreCreate = new RefrigeratoreCreate(requestFunction, (Refrigerator) product1,seller);
        } else if ("Stove".equals(o)) {
            StoveCreate stoveCreate = new StoveCreate(requestFunction, (Stove) product1,seller);
        } else if ("Food".equals(o)) {
            FoodCreate foodCreate = new FoodCreate(requestFunction, (Food) product1,seller);
        }

    }

}
