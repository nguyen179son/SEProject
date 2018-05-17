package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://bccccg4or-mysql.services.clever-cloud.com:3306/bccccg4or?useSSL=false";
            String user      = "uusa0ackteh1ws7o";
            String password  = "XwizjSmS5NJJtewzG2Y";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

