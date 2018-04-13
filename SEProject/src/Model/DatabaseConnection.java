package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3306/SEProject?useSSL=false";
            String user      = "root";
            String password  = "root";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

