package Model;

import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import Helper.Convertor;
import java.sql.*;
import Helper.EmailSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String email;
    private String password;
    private String name;
    private String confirmationCode;
    private String token;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/SEProject?useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public User(String email, String password, String name) {
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.confirmationCode = EmailSender.generateConfirmationCode(12);
        this.token = EmailSender.generateConfirmationCode(12);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save() {
        //Connection conn = null;
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = null;
        boolean returnValue = false;

        try {

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT into user_info (email, password, user_name, confirm_code, token, confirm)" +
                    " VALUES (\"" + email + "\",\"" + password + "\",\"" + name + "\",\"" + confirmationCode + "\",\"" + token + "\",\"" + "0" +
                    "\")";
            int rs = stmt.executeUpdate(sql);
            EmailSender.send(email, confirmationCode);

            stmt.close();
            conn.close();
        } catch (
                SQLException se)

        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e)

        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally

        {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public String getProfile(int id) {
        Connection conn = null;
        Statement stmt = null;
        JSONArray obj = null;
        Convertor convertor = new Convertor();
        boolean returnValue = false;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT email, user_name, phone_number, DOB from user_info WHERE userID=" + id;
            ResultSet rs = stmt.executeQuery(sql);
            obj=convertor.convertToJSON(rs);


            stmt.close();
            conn.close();
        } catch (
                SQLException se)

        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally

        {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        JSObject result = null;

        return obj.toString();
    }

    public boolean exist(int id) {
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user_info WHERE userID="+id;
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                returnValue = false;
            } else {
                returnValue = true;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (
                SQLException se)

        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e)

        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally

        {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return returnValue;
    }

}
