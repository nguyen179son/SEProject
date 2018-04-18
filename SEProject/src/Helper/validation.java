package Helper;

import Model.DatabaseConnection;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validation {
    // JDBC driver name and database URL


    public static boolean UserLoginValidation(String user_name, String password) {
        //Connection conn = null;
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = null;
        boolean returnValue = false;
        try {
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
        } catch (SQLException se)
        {
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

    public static String UserUpdateValidation(String nick_name, String email,
                                               String oldPassword, String newPassword, String confirmNewPassword, String phone){
        String Validation_result = "";

        if (!validation.EmailFormValidation(email)) {
            Validation_result += "Email input has wrong form<br/>";
        }

        if (!validation.nicknameValidation(nick_name)) {
            Validation_result += "Nickname must have less than 255 characters<br/>";
        }

        if (!UserLoginValidation(email,oldPassword)) {
            Validation_result += "Old password incorrect";
        }

        if (!validation.passwordValidation(newPassword)) {
            Validation_result += "New password must contain at " +
                    "least eight characters, at least one number and both lower and uppercase letters and special characters<br/>";
        }

        if (!validation.passwordConfirm(newPassword,confirmNewPassword)){
            Validation_result+="Confirm password does not match";
        }

        if (!validation.UniqueEmailValidation(email)){
            Validation_result+="Email already existed";
        }

        return Validation_result;
    }

    public String UserRegisterValidation(String nickname, String email, String password, String confirm_password) {
        String Validation_result = "";

        if (!validation.EmailFormValidation(email)) {
            Validation_result += "Email input has wrong form<br/>";
        }

        if (!validation.nicknameValidation(nickname)) {
            Validation_result += "Nickname must have less than 255 characters<br/>";
        }

        if (!validation.passwordValidation(password)) {
            Validation_result += "Password must contain at " +
                    "least eight characters, at least one number and both lower and uppercase letters and special characters<br/>";
        }

        if (!validation.passwordConfirm(password,confirm_password)){
            Validation_result+="Confirm password does not match";
        }
        if (!validation.UniqueEmailValidation(email)){
            Validation_result+="Email already existed";
        }
        return Validation_result;
    }

    public static boolean UniqueEmailValidation(String email) {
        //Connection conn = null;
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = null;
        boolean returnValue = false;
        try {
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
}
