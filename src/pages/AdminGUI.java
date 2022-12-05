package pages;

import exceptions.MyExceptions;
import guiProducts.MainPageShow;
import products.*;
import request.ProductRequest;
import users.Admin;
import users.Seller;

import javax.swing.*;
import java.util.ArrayList;


public class AdminGUI extends JFrame {

    private final Admin admin;
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
    private JButton butOK;
    private JPanel Panel1;
    private JList<ProductRequest> listAddProducts;
    private JList<ProductRequest> listRemoveProduct;
    private JList<ProductRequest> listEditProduct;
    private JButton butAddPok;
    private JButton butAddPcancel;
    private JButton butRemovePok;
    private JButton butRmovePcancel;
    private JButton butEditPok;
    private JButton butEditPcancel;
    private JList<Seller> listAddSellers;
    private JList<Seller> listSeller;
    private JList<Product> listProducts;
    private JButton butAddSellerok;
    private JButton butAddSellerCancel;
    private JButton butRemoveSellerOk;
    private JButton butRemoveAllProductOk;


    public AdminGUI(Admin admin) {

        this.admin = admin;

        setFieldsInfo();
        setAddProductList();
        setListEditProduct();
        setListRemoveProduct();
        setListAddSellers();
        setListSeller();
        setListProducts();

        setContentPane(Panel1);
        setVisible(true);
        pack();

        btnHome.addActionListener(e -> {
            new MainPageShow(admin);
            dispose();
        });


        butOK.addActionListener(e -> {

            admin.setFirstName(textFirtName.getText());
            admin.setLastName(textLastName.getText());
            admin.setEmail(textEmail.getText());
            admin.setPhone(textPhone.getText());

            if (!textField1.getText().equals(" ")) {
                try {
                    admin.setPassword(textField1.getText(), textField2.getText());
                } catch (MyExceptions ex) {
                    JOptionPane.showInputDialog(ex.getMessage());
                    MainPage.saveExceptions(ex);
                }
            }

            setFieldsInfo();
            textField1.setText(" ");
            textField2.setText(" ");

        });


        butAddPok.addActionListener(e -> {
            ProductRequest productRequest = listAddProducts.getSelectedValue();
            productRequest.getSeller().setProducts(productRequest.getProduct().get(0));
            productRequest.getSeller().getSellerGUI().setProducts();
            productRequest.getProduct().get(0).setAccepted(true);
            admin.setProducts(productRequest.getProduct().get(0));
            admin.getProductRequestsAdd().remove(listAddProducts.getSelectedValue());
            setAddProductList();
            setListProducts();
        });

        butAddPcancel.addActionListener(e -> {
            admin.getProductRequestsAdd().remove(listAddProducts.getSelectedValue());
            setAddProductList();
        });


        butRemovePok.addActionListener(e -> {

            listRemoveProduct.getSelectedValue().getSeller().getProducts().remove(listRemoveProduct.getSelectedValue().getProduct().get(0));
            listRemoveProduct.getSelectedValue().getSeller().getSellerGUI().setProducts();
            listRemoveProduct.getSelectedValue().getProduct().get(0).remove();
            Admin.getAdmin().getProducts().remove(listRemoveProduct.getSelectedValue().getProduct().get(0));
            Admin.getAdmin().getProductRequestsRemove().remove(listRemoveProduct.getSelectedValue());
            setListRemoveProduct();
            setListProducts();
        });

        butRmovePcancel.addActionListener(e -> {
            Admin.getAdmin().getProductRequestsRemove().remove(listRemoveProduct.getSelectedValue());
            setListRemoveProduct();
        });


        butEditPok.addActionListener(e -> {
            ProductRequest productRequest = listEditProduct.getSelectedValue();
            Product previous = productRequest.getProduct().get(0);
            Product newProduct = productRequest.getProduct().get(1);
            previous.changeInfo(newProduct);
            productRequest.getSeller().getSellerGUI().setProducts();
            Admin.getAdmin().getProductRequestEdit().remove(productRequest);
            setListEditProduct();
         });

        butEditPcancel.addActionListener(e -> {
            Admin.getAdmin().getProductRequestEdit().remove(listEditProduct.getSelectedValue());
            setListEditProduct();
        });


        butAddSellerok.addActionListener(e -> {
            Seller seller = listAddSellers.getSelectedValue();
            Admin.getAdmin().setSellers(seller);
            try {
                seller.setAccepted(true);
            } catch (Exception ex) {
                JOptionPane.showInputDialog(ex.getMessage());
                MainPage.saveExceptions(ex);
            }
            Admin.getAdmin().getSellerRequests().remove(listAddSellers.getSelectedValue());
            setListAddSellers();
            setListSeller();
        });

        butAddSellerCancel.addActionListener(e -> {
           Admin.getAdmin().getSellerRequests().remove(listSeller.getSelectedValue());
           setListAddSellers();
        });


        butRemoveSellerOk.addActionListener(e -> {
            Seller seller = listSeller.getSelectedValue();
            for (Product product : seller.getProducts()) {
                for (int i = Admin.getAdmin().getProducts().size() - 1; i >= 0; i--)
                    if (Admin.getAdmin().getProducts().get(i).getID().equals(product.getID())) {
                        Admin.getAdmin().getProducts().get(i).remove();
                        admin.getProducts().remove(Admin.getAdmin().getProducts().get(i));
                    }
            }
            seller.remove();
            Admin.getAdmin().getSellers().remove(seller);
            setListProducts();
            setListSeller();
        });

        butRemoveAllProductOk.addActionListener(e -> {
            Product product = listProducts.getSelectedValue();
            Seller seller = product.getSeller();
            seller.getProducts().remove(product);
            //seller.getSellerGUI().setProducts();
            admin.getProducts().remove(product);
            setListProducts();
        });

    }

    private void setFieldsInfo() {
        userNameLabel.setText(String.valueOf(admin.getID()));
        textFirtName.setText(admin.getFirstName());
        textLastName.setText(admin.getLastName());
        textEmail.setText(admin.getEmail());
        textPhone.setText(admin.getPhone());
    }

    private void setAddProductList() {
        DefaultListModel<ProductRequest> model = new DefaultListModel<>();
        for (ProductRequest productRequest : Admin.getAdmin().getProductRequestsAdd())
            model.addElement(productRequest);
        listAddProducts.setModel(model);
    }

    private void setListRemoveProduct() {
        DefaultListModel<ProductRequest> model = new DefaultListModel<>();
        for (ProductRequest productRequest : Admin.getAdmin().getProductRequestsRemove())
            model.addElement(productRequest);
        listRemoveProduct.setModel(model);
    }

    private void setListEditProduct() {
        DefaultListModel<ProductRequest> model = new DefaultListModel<>();
        for (ProductRequest productRequest : Admin.getAdmin().getProductRequestEdit()) {
            model.addElement(productRequest);
        }
        listEditProduct.setModel(model);
    }

    private void setListAddSellers() {
        DefaultListModel<Seller> model = new DefaultListModel<>();
        for (Seller seller : Admin.getAdmin().getSellerRequests())
            model.addElement(seller);
        listAddSellers.setModel(model);
    }

    private void setListSeller() {
        DefaultListModel<Seller> model = new DefaultListModel<>();
        for (Seller seller : Admin.getAdmin().getSellers())
            model.addElement(seller);
        listSeller.setModel(model);
    }

    private void setListProducts() {
        DefaultListModel<Product> model = new DefaultListModel<>();
        for (Product product : Admin.getAdmin().getProducts())
            model.addElement(product);
        listProducts.setModel(model);
    }

}
