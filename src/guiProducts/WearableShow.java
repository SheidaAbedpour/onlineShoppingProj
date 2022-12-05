package guiProducts;

import pages.*;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;

public class WearableShow extends JFrame {
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAccount;
    private JButton butClothes;
    private JButton butShoes;
    private JTextField searchField;
    private JButton butBack;
    private JPanel Panel1;
    private PersonalInformation person = null;

    public WearableShow(PersonalInformation person) {

        this.person = person;

        setTitle("Wearable show");
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


        butClothes.addActionListener(e -> {
            new ClothesShow(person);
            dispose();
        });


        butShoes.addActionListener(e -> {
            new ShoesShow(person);
            dispose();
        });

        butLogin.addActionListener(e -> {
            new loginGUI();
            dispose();
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

    }
}
