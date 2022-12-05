package pages;

import guiProducts.MainPageShow;
import products.Product;
import users.Admin;
import users.PersonalInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search extends JFrame {
    private JPanel Panel;
    private JButton btnHome;
    private JPanel Panel1;
    private JList<Product> list;

    public Search(String Name, PersonalInformation person) {

        DefaultListModel<Product> model = new DefaultListModel<>();
        for (Product product : Admin.getAdmin().getProducts())
            if (product.getName().equals(Name))
                model.addElement(product);
        list.setModel(model);

        setContentPane(Panel1);
        setVisible(true);
        pack();


        btnHome.addActionListener(e -> {
            new MainPageShow(person);
            dispose();
        });
    }

}
