package MakeProductGUI;

import exceptions.MyExceptions;
import pages.MainPage;
import products.Clothes;
import products.Mobile;
import products.Product;
import request.ProductRequest;
import request.RequestFunction;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.util.ArrayList;

public class ClothesCreate extends JFrame {

    private Clothes clothes;
    private JTextField textID;
    private JTextField textName;
    private JTextField textBrand;
    private JTextField textPrice;
    private JTextField textNumber;
    private JTextField textDescription;
    private JTextField textCountry;
    private JTextField textMaterial;
    private JTextField textSize;
    private JTextField textKind;
    private JButton OKButton;
    private JPanel Panel1;
    private JComboBox<String> comboKind;


    public ClothesCreate(RequestFunction requestFunction, Clothes clothes1, Seller seller) {

        if (clothes1 != null) {
            textID.setText(clothes1.getID());
            textName.setText(clothes1.getName());
            textBrand.setText(clothes1.getBrand());
            textPrice.setText(String.valueOf(clothes1.getPrice()));
            textNumber.setText(String.valueOf(clothes1.getNumber()));
            textDescription.setText(clothes1.getDescription());
            textCountry.setText(clothes1.getCountry());
            textMaterial.setText(clothes1.getMaterial());
            textSize.setText(clothes1.getSize());
            comboKind.setSelectedItem(String.valueOf(clothes1.getKind()));
        }

        setContentPane(Panel1);
        setVisible(true);
        pack();

        String[] kinds = {"Skirt", "Hoodie", "Vest", "Dress", "Socks", "Coat", "Cap", "Scarf"};
        for (String s : kinds)
            comboKind.addItem(s);


        OKButton.addActionListener(e -> {

            try {
                clothes = new Clothes(textID.getText(),textName.getText(),textBrand.getText(),Double.parseDouble(textPrice.getText()),
                        seller,Integer.parseInt(textNumber.getText()), textDescription.getText(),textCountry.getText(),textMaterial.getText(),
                        textSize.getText(),(String) comboKind.getSelectedItem());
                setRequest(requestFunction,clothes1,seller);
            } catch (MyExceptions ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog(ex.getMessage());
            }

            setVisible(false);

        });


    }

    public void setRequest(RequestFunction requestFunction, Product product1, Seller seller) {

        ArrayList<Product> products = new ArrayList<>();
        Product product2 = getClothes();
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

    public Clothes getClothes() {
        return clothes;
    }
}
