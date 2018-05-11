package Helper;

import Model.DatabaseConnection;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    // JDBC driver name and database URL


    public static boolean UserLoginValidation(String user_name, String password) {
        //Connection conn = null;
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user_info WHERE email=\"" + user_name + "\" AND password=\"" + password + "\"";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                returnValue = false;
            } else {
                returnValue = true;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
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

    public static String UserRegisterValidation(String nickname, String email, String password, String confirm_password) {
        String Validation_result = "";

        if (!Validation.nicknameValidation(nickname)) {
            Validation_result += "Nickname must have less than 255 characters<br/>";
        }

        else if (!Validation.EmailFormValidation(email)) {
            Validation_result += "Email input has wrong form<br/>";
        }

        else if (!Validation.UniqueEmailValidation(email)){
            Validation_result+="Email already existed";
        }

        else if (!Validation.passwordValidation(password)) {
            Validation_result += "Password must contain at " +
                    "least eight characters, at least one number and both lower and uppercase letters and special characters";
        }

        else if (!Validation.passwordConfirm(password,confirm_password)){
            Validation_result+="Confirm password does not match";
        }
        return Validation_result;
    }

    public static boolean UniqueEmailValidation(String email) {
        //Connection conn = null;
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user_info WHERE email=\"" + email + "\"";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                returnValue = true;
            } else {
                returnValue = false;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
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

    public static boolean nicknameValidation(String nickname) {
        return nickname.length() < 255 && nickname.length() != 0;
    }

    public static boolean EmailFormValidation(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile( "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean passwordValidation(String password) {
        Pattern VALID_PASSWORD = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher matcher = VALID_PASSWORD.matcher(password);
        return matcher.find();
        //Regex for password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters
    }

    public static boolean passwordConfirm(String password, String confirm_password) {
        return password.equals(confirm_password);
    }

    public static boolean checkConfirmationCode(String email, String confirmationCode){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT confirm_code from user_info WHERE email = " + "\"" + email + "\"";
            ResultSet rs = stmt.executeQuery(sql);


            if (rs.next()){
                if(confirmationCode.equals(rs.getString("confirm_code"))) {
                    stmt.close();
                    conn.close();
                    return true;
                }
                else {
                    stmt.close();
                    conn.close();
                    return false;
                }
            }
            else {
                stmt.close();
                conn.close();
                return false;
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
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
        return false;
    }
}
