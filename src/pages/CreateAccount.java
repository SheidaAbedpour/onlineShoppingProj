package pages;

import exceptions.MyExceptions;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAccount extends JFrame {
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAcoount;
    private JTextField searchField;
    private JPanel Panel1;
    private JTextField textFirstName;
    private JButton OKButton;
    private JTextField textEmail;
    private JTextField textPhone;
    private JTextField textPass;
    private JComboBox<String> comboxPossion;
    private JTextField textCredit;
    private JTextField textLastName;
    private PersonalInformation person;

    public CreateAccount() {

        setContentPane(Panel1);
        setVisible(true);
        pack();

        comboxPossion.addItem("Seller");
        comboxPossion.addItem("Consumer");


        OKButton.addActionListener(e -> {

            String FirstName = textFirstName.getText();
            String LastName = textLastName.getText();
            String Email = textEmail.getText();
            String Phone = textPhone.getText();
            String Pass = textPass.getText();
            double credit = Double.parseDouble(textCredit.getText());
            String Position = (String) comboxPossion.getSelectedItem();


            if (Position == null){
                JOptionPane.showInputDialog("choose position");
            }

            else if (Position.equals("Seller")) {
                try {
                    try {
                        person = new Seller(FirstName,LastName,Email,Phone,Pass,credit);
                    } catch (SQLException ex) {
                        MainPage.saveExceptions(ex);
                        throw new RuntimeException(ex);
                    }
                    new SellerGUI((Seller) person);
                    dispose();
                } catch (MyExceptions | IOException ex) {
                    MainPage.saveExceptions(ex);
                    JOptionPane.showInputDialog(ex.getMessage());
                }
            }

            else if (Position.equals("Consumer")) {
                try {
                    try {
                        person = new Consumer(FirstName,LastName,Email,Phone,Pass,credit);
                    } catch (SQLException ex) {
                        MainPage.saveExceptions(ex);
                        throw new RuntimeException(ex);
                    }
                    new ConsumerGUI((Consumer) person);
                    dispose();
                } catch (MyExceptions | IOException ex) {
                    JOptionPane.showInputDialog(ex.getMessage());
                    MainPage.saveExceptions(ex);
                }
            }

        });
    }

    public PersonalInformation getPerson() {
        return person;
    }

}
