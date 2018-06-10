package Model;


import org.apache.commons.dbcp.*;
import javax.sql.DataSource;
import java.sql.*;

public class DatabaseConnection {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    /*
    public static final String URL = "jdbc:mysql://bccccg4or-mysql.services.clever-cloud.com:3306/bccccg4or?useSSL=false";
    public static final String USERNAME = "uusa0ackteh1ws7o";
    public static final String PASSWORD = "XwizjSmS5NJJtewzG2Y";
    */

    public static final String URL = "jdbc:mysql://bn43torgf-mysql.services.clever-cloud.com:3306/bn43torgf?useSSL=false";
    public static final String USERNAME = "uwgusnhbxi0m34us";
    public static final String PASSWORD = "lY5FEv633YWFnHptzUh";


    private static DataSource source = null;
    public static Connection getConnection() throws Exception{
        Connection conn = null;
        if(source == null) {
            source = new org.apache.commons.dbcp.BasicDataSource();
            ((BasicDataSource) source).setDriverClassName(DRIVER);
            ((BasicDataSource) source).setUsername(USERNAME);
            ((BasicDataSource) source).setPassword(PASSWORD);
            ((BasicDataSource) source).setUrl(URL);
        }
        try {
            conn = source.getConnection();
        }
        catch(Exception e){
            e.printStackTrace();
            ((BasicDataSource) source).close();
            source = null;
            conn = getConnection();
        }
        return conn;
    }

}

