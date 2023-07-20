package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseController
{
    public static void openDataBase(String query) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/userdatas?user=root";
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        statement.execute(query);

        statement.close();
        connection.close();
    }
}
