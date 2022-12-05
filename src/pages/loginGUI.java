package pages;

import guiProducts.MainPageShow;
import users.Admin;
import users.Consumer;
import users.PersonalInformation;
import users.Seller;

import javax.swing.*;
import java.util.Arrays;

public class loginGUI extends JFrame {
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAcoount;
    private JPanel Panel1;
    private JTextField textID;
    private JPasswordField passwordField1;
    private JButton OKButton;
    private JButton butCreateAccount;
    private PersonalInformation person;

    public loginGUI() {

        setContentPane(Panel1);
        setVisible(true);
        pack();

        btnHome.addActionListener(e -> {
            new MainPageShow(person);
            dispose();
        });


        OKButton.addActionListener(e -> {
            String id = textID.getText();
            String pass = String.valueOf(passwordField1.getPassword());

            for (Seller seller : Admin.getAdmin().getSellers())
                if (seller.getID() == Integer.parseInt(id))
                    if (seller.getPassword().equals(pass)) {
                        person = seller;
                        new SellerGUI((Seller) person);
                        dispose();
                        return;
                    }


            for (Consumer consumer : Admin.getAdmin().getConsumers())
                if (consumer.getID() == Integer.parseInt(id))
                    if (consumer.getPassword().equals(pass)) {
                        person = consumer;
                        new ConsumerGUI((Consumer) person);
                        dispose();
                        return;
                    }

            if (Admin.getAdmin().getID() == Integer.parseInt(id))
                if (Admin.getAdmin().getPassword().equals(pass)) {
                    person = Admin.getAdmin();
                    new AdminGUI((Admin) person);
                    dispose();
                    return;
                }

            JOptionPane.showInputDialog("try again");

        });


        butCreateAccount.addActionListener(e -> {
            CreateAccount createAccount = new CreateAccount();
            setVisible(false);
            person = createAccount.getPerson();
        });
    }

}
