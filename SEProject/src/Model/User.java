package Model;

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
}
