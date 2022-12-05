package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySql {

    private final String URL = "jdbc:mysql://localhost:3306/shopping";
    private final String user = "user";
    private final String pass = "pass";

    public ResultSet myExecuteQuery(String cmd) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, user, pass);
            Statement statement = connection.prepareStatement(cmd);
            return statement.executeQuery(cmd);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void myExecuteSQL(String cmd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, user, pass);
            Statement statement = connection.prepareStatement(cmd);
            statement.execute(cmd);
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
