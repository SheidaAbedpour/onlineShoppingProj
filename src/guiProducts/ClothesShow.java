package guiProducts;

import comments.Comment;
import comments.Rank;
import factor.BuyFactor;
import pages.*;
import products.Clothes;
import products.Product;
import products.ProductKind;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClothesShow extends JFrame {


    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAccounr;
    private JPanel mobiles;
    private JScrollPane scrollmob;
    private JList<Clothes> listClothes;
    private JButton butBack;
    private JButton butFilter;
    private JPanel Panel1;
    private JButton rankButton;
    private JButton buyButton;
    private JButton commentButton;
    private JTextField textComment;
    private JComboBox<Integer> comboRank;
    private PersonalInformation person = null;

    public ClothesShow(PersonalInformation person) {

        this.person = person;

        setClothes(Admin.getAdmin().getProducts());

        setContentPane(Panel1);
        setVisible(true);
        pack();

        butBack.addActionListener(e -> {
          new WearableShow(person);
          dispose();
        });

        btnHome.addActionListener(e -> {
            new MainPageShow(person);
            dispose();
        });


        butFilter.addActionListener(e -> {
            new ClothesFilterGUI(this);
            setVisible(false);
        });


        butAccounr.addActionListener(e -> {
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
                for (Clothes product : listClothes.getSelectedValuesList())
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
                Product product = listClothes.getSelectedValue();
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

            Product clothes = listClothes.getSelectedValue();

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

    public void setClothes(ArrayList<Product> products) {
        DefaultListModel<Clothes> model = new DefaultListModel<>();
        for (Product product : products)
            if (product.getProductKind().equals(ProductKind.CLOTHES))
                model.addElement((Clothes) product);
        listClothes.setModel(model);
    }

    public PersonalInformation getPerson() {
        return person;
    }

    public void setPerson(PersonalInformation person) {
        this.person = person;
    }
}
