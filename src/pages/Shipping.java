package pages;

import exceptions.LackOfMoney;
import factor.BuyFactor;
import factor.SellingFactor;
import guiProducts.MainPageShow;
import products.Product;
import users.Admin;
import users.Consumer;
import users.Seller;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Shipping extends JFrame {
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butAccounr;
    private JPanel mobiles;
    private JScrollPane scrollmob;
    private JButton butBack;
    private JList<Product> listProducts;
    private JLabel Price;
    private JTextField textAdrress;
    private JButton okButton;
    private JPanel Panel1;
    private Consumer consumer;


    public Shipping(Consumer consumer){

        this.consumer = consumer;

        setContentPane(Panel1);
        setVisible(true);
        pack();

        DefaultListModel<Product> model = new DefaultListModel<>();
        for (Product product : consumer.getShop())
            model.addElement(product);
        listProducts.setModel(model);

        double price = 0;
        for (Product product : consumer.getShop())
            price += product.getPrice();

        Price.setText(String.valueOf(price));


        okButton.addActionListener(e -> {

            double price2 = 0;
            for (Product product : consumer.getShop())
                price2 += product.getPrice();

            if (consumer.getCredit() < price2)
                try {
                    throw new LackOfMoney();
                } catch (LackOfMoney ex) {
                    MainPage.saveExceptions(ex);
                    JOptionPane.showInputDialog(ex.getMessage());
                }

            else{
                consumer.setCredit(consumer.getCredit() - price2);
                for (Product product : consumer.getShop()) {
                    product.setNumber(product.getNumber() - 1);
                    for (Seller seller : Admin.getAdmin().getSellers())
                        for (Product product1 : seller.getProducts())
                            if (product1.getID().equals(product.getID())) {
                                seller.setCredit(seller.getCredit() + product.getPrice());

                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                LocalDateTime now = LocalDateTime.now();
                                String date = dtf.format(now);

                                BuyFactor buyFactor = new BuyFactor(date, price2, consumer.getShop(), seller, true);
                                SellingFactor sellingFactor = new SellingFactor(date, price2, consumer.getShop(), consumer, true);

                                try {
                                    consumer.setBuyFactors(buyFactor);
                                    seller.setSellingFactors(sellingFactor);
                                } catch (IOException ex) {
                                    MainPage.saveExceptions(ex);
                                    JOptionPane.showInputDialog(ex.getMessage());
                                }
                            }
                }
            }

        });


        btnHome.addActionListener(e -> {
            new MainPageShow(consumer);
            dispose();
        });


        butBack.addActionListener(e -> {
            new MainPageShow(consumer);
            dispose();
        });


        butAccounr.addActionListener(e -> {
            new ConsumerGUI(consumer);
            dispose();
        });
    }

}
