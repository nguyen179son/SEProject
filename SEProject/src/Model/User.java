package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String email;
    private String password;
    private String name;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/SEProject";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public User(String email, String password, String name) {
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
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
            sql = "INSERT into user_info (email, password, user_name) VALUES (\"" + email + "\",\"" + password + "\",\"" + name +
                    "\")";
            int rs = stmt.executeUpdate(sql);


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
