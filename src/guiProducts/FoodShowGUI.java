package guiProducts;

import comments.Comment;
import comments.Rank;
import factor.BuyFactor;
import pages.*;
import products.Food;
import products.Product;
import products.ProductKind;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class FoodShowGUI extends JFrame {
    private JPanel Panel1;
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAccount;
    private JPanel mobiles;
    private JScrollPane scrollmob;
    private JList<Food> listFood;
    private JButton butBack;
    private JButton butFilter;
    private JButton commentButton;
    private JButton rankButton;
    private JButton buyButton;
    private JTextField textComment;
    private JComboBox<Integer> comboRank;
    private PersonalInformation person = null;

    public FoodShowGUI(PersonalInformation person) {

        this.person = person;

        setListFood(Admin.getAdmin().getProducts());

        setContentPane(Panel1);
        setVisible(true);
        pack();

        butBack.addActionListener(e -> {
            new MainPageShow(person);
            dispose();
        });

        btnHome.addActionListener(e -> {
            new MainPageShow(person);
            dispose();
        });

        butFilter.addActionListener(e -> {
            new FoodFilterGUI(this);
            setVisible(false);
        });


        butAccount.addActionListener(e -> {
            if (person == null){
                JOptionPane.showInputDialog("login!");
                new loginGUI();
                dispose();
            }
            else if (person.getPosition().equals("Admin")){
                new AdminGUI((Admin) person);
                dispose();
            }
            else if (person.getPosition().equals("Seller")){
                new SellerGUI((Seller) person);
                dispose();
            }
            else if (person.getPosition().equals("Consumer")){
                new ConsumerGUI((Consumer) person);
                dispose();
            }
        });

        butCart.addActionListener(e -> {
            if (!person.getPosition().equals("Consumer")) {
                JOptionPane.showInputDialog("login");
                new loginGUI();
                dispose();
            }

            else {
                new Shipping((Consumer) person);
                dispose();
            }
        });


        buyButton.addActionListener(e -> {
            if (person == null) {
                JOptionPane.showInputDialog("login");
                new loginGUI();
                dispose();
            }
            else if (person.getPosition().equals("Consumer")) {
                for (Food product : listFood.getSelectedValuesList())
                    ((Consumer) person).setShop(product);
            }
        });


        commentButton.addActionListener(e -> {
            if (person == null) {
                JOptionPane.showInputDialog("login");
                new loginGUI();
                dispose();
            }
            else if (person.getPosition().equals("Consumer")) {
                Product product = listFood.getSelectedValue();
                Comment comment = new Comment((Consumer) person,product,textComment.getText());
                try {
                    product.setComments(comment);
                } catch (IOException ex) {
                    MainPage.saveExceptions(ex);
                    JOptionPane.showInputDialog(ex.getMessage());
                }
            }
        });

        comboRank.addItem(1);
        comboRank.addItem(2);
        comboRank.addItem(3);
        comboRank.addItem(4);
        comboRank.addItem(5);

        rankButton.addActionListener(e -> {

            Product clothes = listFood.getSelectedValue();

            if (person == null){
                JOptionPane.showInputDialog("login");
                new loginGUI();
                dispose();
            }
            else if (person.getPosition().equals("Consumer")) {
                for (BuyFactor buyFactor : ((Consumer) person).getBuyFactors())
                    for (Product product : buyFactor.getProducts())
                        if (product.getID().equals(clothes.getID())) {
                            Rank rank = new Rank((Consumer) person, (int) comboRank.getSelectedItem(),clothes);
                            try {
                                clothes.setRanks(rank);
                            } catch (IOException ex) {
                                JOptionPane.showInputDialog(ex.getMessage());
                            }
                        }
            }
        });


    }

    public void setListFood(ArrayList<Product> products) {
        DefaultListModel<Food> model = new DefaultListModel<>();
        for (Product product : products)
            if (product.getProductKind().equals(ProductKind.FOOD))
                model.addElement((Food) product);
        listFood.setModel(model);
    }

}
