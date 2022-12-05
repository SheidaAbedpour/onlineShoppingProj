package guiProducts;

import pages.*;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;

public class FurnitureShow extends JFrame {
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAccount;
    private JButton butTV;
    private JButton butStove;
    private JTextField searchField;
    private JButton butBack;
    private JButton butRefrigerator;
    private JPanel Panel1;
    private PersonalInformation person = null;

    public FurnitureShow(PersonalInformation person) {

        this.person = person;

        setVisible(true);
        setTitle("Furniture show");
        setContentPane(Panel);
        pack();

        btnHome.addActionListener(e -> {
            new MainPageShow(person);
            setVisible(false);
        });

        butBack.addActionListener(e -> {
            new MainPageShow(person);
            setVisible(false);
        });


        butTV.addActionListener(e -> {
            new TVshow(this,person);
            setVisible(false);
        });


        butRefrigerator.addActionListener(e -> {
            new RefrigeratorShow(this,person);
            setVisible(false);
        });


        butStove.addActionListener(e -> {
            new StoveShow(this,person);
            setVisible(false);
        });


        butStove.addActionListener(e -> {
          new StoveShow(this,person);
          setVisible(false);
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
