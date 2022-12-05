package guiProducts;

import pages.*;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageShow extends JFrame {
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAccount;
    private JTextField searchField;
    private JButton butFurniture;
    private JButton butDigital;
    private JButton butWearable;
    private JButton butFood;
    private JPanel Panel;
    private JPanel jPanel;
    private JButton serachButton;
    private PersonalInformation person = null;

    public MainPageShow(PersonalInformation person) {

        this.person = person;

        setTitle("Main Page");
        setContentPane(Panel);
        setVisible(true);
        pack();

        butDigital.addActionListener(e -> {
            new DigitalShow(person);
            dispose();
        });

        butFurniture.addActionListener(e -> {
            new FurnitureShow(person);
            dispose();
        });

        butWearable.addActionListener(e -> {
            new WearableShow(person);
            dispose();
        });


        butFood.addActionListener(e -> {
            new FoodShowGUI(person);
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
            try {
                if (!person.getPosition().equals("Consumer")) {
                    JOptionPane.showInputDialog("login");
                    new loginGUI();
                    dispose();
                }

                    else {
                        new Shipping((Consumer) person);
                        dispose();
                    }
            }catch (NullPointerException ex) {
                MainPage.saveExceptions(ex);
                JOptionPane.showInputDialog("login");
                new loginGUI();
                dispose();
            }

        });


        serachButton.addActionListener(e -> {
            new Search(searchField.getText(),person);
            dispose();
        });
    }

}
