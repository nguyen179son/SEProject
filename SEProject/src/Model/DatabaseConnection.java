package Model;


import org.apache.commons.dbcp.*;
import javax.sql.DataSource;
import java.sql.*;

public class DatabaseConnection {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://bccccg4or-mysql.services.clever-cloud.com:3306/bccccg4or?useSSL=false";
    public static final String USERNAME = "uusa0ackteh1ws7o";
    public static final String PASSWORD = "XwizjSmS5NJJtewzG2Y";
    private static DataSource source = null;
    public static Connection getConnection() throws Exception{
        if(source == null) {
            source = new org.apache.commons.dbcp.BasicDataSource();
            ((BasicDataSource) source).setDriverClassName(DRIVER);
            ((BasicDataSource) source).setUsername(USERNAME);
            ((BasicDataSource) source).setPassword(PASSWORD);
            ((BasicDataSource) source).setUrl(URL);
        }
        return source.getConnection();

    }

}

