package pages;

import exceptions.MyExceptions;
import factor.BuyFactor;
import guiProducts.MainPageShow;
import users.Consumer;

import javax.swing.*;

public class ConsumerGUI extends JFrame {

    private final Consumer consumer;
    private JPanel Panel;
    private JButton btnHome;
    private JButton butCart;
    private JButton butLogin;
    private JButton butAcoount;
    private JTextField searchField;
    private JPanel Panel1;
    private JLabel ueraNameLabel;
    private JTextField textFirtName;
    private JTextField textLastName;
    private JTextField textEmail;
    private JTextField textPhone;
    private JLabel userNameLabel;
    private JTextField textCredit;
    private JList<BuyFactor> listFactor;
    private JButton butOK;
    private JTextField textField1;
    private JTextField textField2;


    public ConsumerGUI(Consumer consumer) {

        this.consumer = consumer;

        setContentPane(Panel1);
        setVisible(true);
        pack();

        setFields();

        btnHome.addActionListener(e -> {
            new MainPageShow(consumer);
            dispose();
        });


        butOK.addActionListener(e -> {

            consumer.setFirstName(textFirtName.getText());
            consumer.setLastName(textLastName.getText());
            consumer.setEmail(textEmail.getText());
            consumer.setPhone(textPhone.getText());
            consumer.setCredit(Double.parseDouble(textCredit.getText()));

            if (!textField1.getText().equals("")) {
                try {
                    consumer.setPassword(textField1.getText(), textField2.getText());
                } catch (MyExceptions ex) {
                    JOptionPane.showInputDialog(ex.getMessage());
                    MainPage.saveExceptions(ex);
                }
            }

            setFields();
            textField1.setText(" ");
            textField2.setText(" ");

        });

    }

    private void setFields() {

        userNameLabel.setText(String.valueOf(consumer.getID()));
        textFirtName.setText(consumer.getFirstName());
        textLastName.setText(consumer.getLastName());
        textEmail.setText(consumer.getEmail());
        textPhone.setText(consumer.getPhone());
        textCredit.setText(String.valueOf(consumer.getCredit()));

        DefaultListModel<BuyFactor> buyFactorDefaultListModel = new DefaultListModel<>();
        for (BuyFactor buyFactor : consumer.getBuyFactors())
            buyFactorDefaultListModel.addElement(buyFactor);
        listFactor.setModel(buyFactorDefaultListModel);
    }

}
