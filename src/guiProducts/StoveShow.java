package guiProducts;

import comments.Comment;
import comments.Rank;
import factor.BuyFactor;
import pages.*;
import products.Product;
import products.ProductKind;
import products.Stove;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class StoveShow extends JFrame {

    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAccount;
    private JPanel mobiles;
    private JScrollPane scrollmob;
    private JList<Stove> listStove;
    private JButton butBack;
    private JButton butFilter;
    private JPanel Panel1;
    private JButton commentButton;
    private JButton rankButton;
    private JButton buyButton;
    private JTextField textComment;
    private JComboBox<Integer> comboRank;
    private PersonalInformation person = null;

    public StoveShow(FurnitureShow furnitureShow, PersonalInformation person) {

        this.person = person;

        setStoves(Admin.getAdmin().getProducts());

        setVisible(true);
        setContentPane(Panel1);
        pack();

        butBack.addActionListener(e -> {
            furnitureShow.setVisible(true);
            dispose();
        });

        btnHome.addActionListener(e -> {
            new MainPageShow(person);
            dispose();
        });

        butFilter.addActionListener(e -> {
            new StoveFilterGUI(this);
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
                for (Stove product : listStove.getSelectedValuesList())
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
                Product product = listStove.getSelectedValue();
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

            Product clothes = listStove.getSelectedValue();

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
                                MainPage.saveExceptions(ex);
                                JOptionPane.showInputDialog(ex.getMessage());
                            }
                        }
            }
        });

    }

    public void setStoves(ArrayList<Product> products) {
        DefaultListModel<Stove> listModel = new DefaultListModel<>();
        for (Product product : products)
            if (product.getProductKind().equals(ProductKind.STOVE))
                listModel.addElement((Stove) product);
        listStove.setModel(listModel);
    }

}
