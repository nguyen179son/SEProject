package Helper;

import Model.DatabaseConnection;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    // JDBC driver name and database URL


    public static boolean loginValidation(String user_name, String password) {
        if (user_name == null || password == null) return false;
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

    public static boolean loginValidation(int userID, String password) {
        if (password == null) return  false;
        //Connection conn = null;
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user_info WHERE userID = " + userID + " AND password=\"" + password + "\"";
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

    public static ObjectNode registerValidation(String nickname, String email, String password, String confirm_password) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();
        result.put("valid", true);
        ArrayNode errorMessage = mapper.createArrayNode();


        if (!Validation.nicknameValidation(nickname)) {
            result.put("valid", false);
            errorMessage.add("Nickname must have less than 255 characters");
        }

        if (!Validation.emailFormValidation(email)) {
            result.put("valid", false);
            errorMessage.add("Email input has wrong form");
        }

        if (!Validation.uniqueEmailValidation(email)) {
            result.put("valid", false);
            errorMessage.add("Email already existed");
        }

        if (!Validation.passwordValidation(password)) {
            result.put("valid", false);
            errorMessage.add("Password must contain at " +
                    "least eight characters, at least one number and both lower and uppercase letters and special characters");
        }

        if (!Validation.passwordConfirm(password, confirm_password)) {
            result.put("valid", false);
            errorMessage.add("Confirm password does not match");
        }
        result.put("error_message", errorMessage);
        return result;
    }

    public static ObjectNode newPasswordValidation(String email, String confirm_code, String password, String confirm_password) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();
        result.put("valid", true);
        ArrayNode errorMessage = mapper.createArrayNode();


        if (Validation.uniqueEmailValidation(email)) {
            result.put("valid", false);
            errorMessage.add("Email doesn't exist");
        }

        if (!checkConfirmationCode(email, confirm_code)) {
            result.put("valid", false);
            errorMessage.add("Incorrect Confirmation Code");
        }

        if (!Validation.passwordValidation(password)) {
            result.put("valid", false);
            errorMessage.add("Password must contain at " +
                    "least eight characters, at least one number and both lower and uppercase letters and special characters");
        }

        if (!Validation.passwordConfirm(password, confirm_password)) {
            result.put("valid", false);
            errorMessage.add("Confirm password does not match");
        }

        result.put("error_message", errorMessage);
        return result;
    }

    public static boolean uniqueEmailValidation(String email) {
        if (email == null) return false;
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
        if (nickname == null) return false;
        return nickname.length() < 255 && nickname.length() != 0;
    }

    public static boolean emailFormValidation(String email) {
        if (email == null) return false;
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean passwordValidation(String password) {
        if (password == null) return false;
        Pattern VALID_PASSWORD = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher matcher = VALID_PASSWORD.matcher(password);
        return matcher.find();
        //Regex for password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters
    }

    public static boolean passwordConfirm(String password, String confirm_password) {
        if(password == null || confirm_password == null) return false;
        return password.equals(confirm_password);
    }

    public static boolean checkConfirmationCode(String email, String confirmationCode) {
        if (confirmationCode == null) return false;
        ObjectNode user_info = User.getProfile(email);
        if (confirmationCode.equals(user_info.get("confirm_code").textValue()))
            return true;
        else return false;
    }

    public static boolean phoneNumberValidation(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.equals("")) return true;
        Pattern VALID_PASSWORD = Pattern.compile("\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})");
        Matcher matcher = VALID_PASSWORD.matcher(phoneNumber);
        if (matcher.find())
            return true;
        VALID_PASSWORD = Pattern.compile("\\(?([0-9]{4})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})");
        matcher = VALID_PASSWORD.matcher(phoneNumber);
        return matcher.find();
    }

    public static boolean dobValidation(String DOB) {
        if (DOB == null || DOB.equals("")) return true;
        String[] components = DOB.split("-");
        if (components.length != 3) return false;
        try {
            if ((Integer.parseInt(components[0]) <= 0) || (Integer.parseInt(components[1]) <= 0) || (Integer.parseInt(components[0]) <= 0))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static ObjectNode changePasswordValidation(int userID, String currentPassword, String newPassword, String confirmPassword) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();
        result.put("valid", true);
        ArrayNode errorMessage = mapper.createArrayNode();

        if (!Validation.loginValidation(userID, currentPassword)) {
            result.put("valid", false);
            errorMessage.add("Incorrect password");
        }
        if (!Validation.passwordValidation(newPassword)) {
            result.put("valid", false);
            errorMessage.add("Password must contain at " +
                    "least eight characters, at least one number and both lower and uppercase letters and special characters");
        }

        if (!Validation.passwordConfirm(newPassword, confirmPassword)) {
            result.put("valid", false);
            errorMessage.add("Confirm password does not match");
        }
        result.put("error_message", errorMessage);

        return result;
    }

    public static ObjectNode profileValidation(String userName, String phoneNumber, String DOB) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();
        result.put("valid", true);
        ArrayNode errorMessage = mapper.createArrayNode();

        if (!Validation.nicknameValidation(userName)) {
            result.put("valid", false);
            errorMessage.add("Nickname must have less than 255 characters");
        }

        if (!Validation.phoneNumberValidation(phoneNumber)) {
            result.put("valid", false);
            errorMessage.add("Invalid phone number");
        }

        if (!Validation.dobValidation(DOB)) {
            result.put("valid", false);
            errorMessage.add("Invalid date of birth");
        }

        result.put("error_message", errorMessage);

        return result;
    }

}
