package users;

import exceptions.EmailFormat;
import exceptions.MyExceptions;
import exceptions.PhoneFormat;
import mySQL.MySql;

import javax.swing.text.Position;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PersonalInformation {

    private int ID;
    private final String Position;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;
    private String Password;
    private double credit;

    PersonalInformation(String position, String firstName, String lastName, String email,
                        String phone, String password, double credit, int id) throws MyExceptions {

        if (phone.length() != 11)
            throw new PhoneFormat();

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new EmailFormat();

        this.ID = id;
        this.Position = position;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Phone = phone;
        this.Password = password;
        this.credit = credit;
    }

    public PersonalInformation(String Position){
        this.Position = Position;
    }

    public void saveEmail() {
        String cmd = String.format("INSERT INTO emails(userID,email) VALUES(%d,'%s')" ,ID,getEmail());
        new MySql().myExecuteSQL(cmd);
    }

    public void savePhone() {
        String cmd = String.format("INSERT INTO phones(userID,phone) VALUES(%d,'%s')",ID,getPhone());
        new MySql().myExecuteSQL(cmd);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPosition() {
        return Position;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
        if (Position.equals("Seller")) {
            String cmd = String.format("UPDATE sellers SET firstName='%s' WHERE id='%s'", FirstName, getID());
            new MySql().myExecuteSQL(cmd);
        }
        else if (Position.equals("Consumer")) {
            String cmd = String.format("UPDATE consumers SET firstName='%s' WHERE id='%s'", FirstName, getID());
            new MySql().myExecuteSQL(cmd);
        }
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
        if (Position.equals("Seller")) {
            String cmd = String.format("UPDATE sellers SET lastName='%s' WHERE id='%s'", LastName, getID());
            new MySql().myExecuteSQL(cmd);
        }
        else if (Position.equals("Consumer")) {
            String cmd = String.format("UPDATE consumers SET lastName='%s' WHERE id='%s'", LastName, getID());
            new MySql().myExecuteSQL(cmd);
        }
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
        String cmd2 = String.format("UPDATE emails SET email='%s' WHERE userID='%s'",Email,getID());
        new MySql().myExecuteSQL(cmd2);
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
        String cmd2 = String.format("UPDATE phones SET phone='%s' WHERE userID='%s'",Phone,getID());
        new MySql().myExecuteSQL(cmd2);
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password, String newPass) throws MyExceptions {
        if (password.equals(this.Password)) {
            this.Password = newPass;
            if (Position.equals("Sellers")) {
                String cmd = String.format("UPDATE sellers SET password='%s' WHERE id='%s'", Password, getID());
                new MySql().myExecuteSQL(cmd);
            }
            else if (Position.equals("Consumer")) {
                String cmd = String.format("UPDATE consumers SET password='%s' WHERE id='%s'", Password, getID());
                new MySql().myExecuteSQL(cmd);
            }
        }
        else
            throw new MyExceptions("wrong password");
    }

    public void setPassword(String password){
        this.Password = password;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", Position=" + Position +
                ", FirstName=" + FirstName +
                ", LastName=" + LastName +
                ", Email=" + Email +
                ", Phone=" + Phone;
    }
}
