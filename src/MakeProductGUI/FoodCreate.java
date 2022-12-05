package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.Food;
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

public class FoodCreate extends JFrame {

    private Food food;
    private JTextField textID;
    private JTextField textName;
    private JTextField textBrand;
    private JTextField textPrice;
    private JTextField textNumber;
    private JTextField textDescription;
    private JButton OKButton;
    private JTextField textDate1;
    private JTextField textDate2;
    private JPanel Panel1;

    public FoodCreate(RequestFunction requestFunction, Food food1,Seller seller) {

        setContentPane(Panel1);
        setVisible(true);
        pack();

        if (food1 != null) {
            textID.setText(food1.getID());
            textName.setText(food1.getName());
            textBrand.setText(food1.getBrand());
            textPrice.setText(String.valueOf(food1.getPrice()));
            textNumber.setText(String.valueOf(food1.getNumber()));
            textDescription.setText(food1.getDescription());
            textDate1.setText(food1.getDateOfManufacture());
            textDate2.setText(food1.getExpirationDate());
        }

        OKButton.addActionListener(e -> {

            try {
                food = new Food(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),textDate1.getText(),textDate2.getText());
                setRequest(requestFunction,food1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);

        });
    }

    public void setRequest(RequestFunction requestFunction, Product product1, Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getFood();
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

    public Food getFood() {
        return food;
    }
}
