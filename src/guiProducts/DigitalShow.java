package guiProducts;

import pages.*;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;

public class DigitalShow extends JFrame {
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAccount;
    private JButton butMobile;
    private JButton butLaptop;
    private JTextField searchField;
    private JButton butBack;
    private JPanel mainPanel;
    private PersonalInformation person = null;

    public DigitalShow(PersonalInformation person) {

        this.person = person;

        setTitle("Digital Products");
        setVisible(true);
        setContentPane(mainPanel);
        pack();

        butBack.addActionListener(e ->  {
            new MainPageShow(person);
            dispose();
        });

        btnHome.addActionListener(e -> {
            new MainPageShow(person);
            dispose();
        });

        butMobile.addActionListener(e -> {
            new MobileShow(person);
            dispose();
        });

        butLaptop.addActionListener(e -> {
            new LaptopShow(person);
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

    public PersonalInformation getPerson() {
        return person;
    }

    public void setPerson(PersonalInformation person) {
        this.person = person;
    }
}
