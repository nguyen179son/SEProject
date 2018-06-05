package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import Helper.Convertor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.*;
import Helper.EmailSender;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class User {
    private int ID;
    private String email;
    private String password;
    private String name;
    private String confirmationCode;
    private boolean isComfirmed;
    private String profilePicture;
    private Calendar DOB;
    private String phoneNunber;

    public User(String email, String password, String name) {
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.confirmationCode = EmailSender.generateConfirmationCode(12);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public boolean isComfirmed() {
        return isComfirmed;
    }

    public void setComfirmed(boolean comfirmed) {
        isComfirmed = comfirmed;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Calendar getDOB() {
        return DOB;
    }

    public void setDOB(Calendar DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNunber() {
        return phoneNunber;
    }

    public void setPhoneNunber(String phoneNunber) {
        this.phoneNunber = phoneNunber;
    }


    public void save() {
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT into user_info (email, password, user_name, confirm_code, confirm)" +
                    " VALUES (\"" + email + "\",\"" + password + "\",\"" + name + "\",\"" + confirmationCode + "\",\"" + "0" +
                    "\")";
            int rs = stmt.executeUpdate(sql);
            EmailSender.send(email, confirmationCode);

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
    }

    public static ObjectNode toUserJSON(ResultSet rs){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        try {
            objectNode1.put("userID", rs.getInt("userID"));
            objectNode1.put("user_name", rs.getString("user_name"));
            objectNode1.put("email", rs.getString("email"));
            objectNode1.put("phone_number", rs.getString("phone_number"));
            objectNode1.put("DOB", rs.getString("DOB"));
            objectNode1.put("profile_picture", rs.getString("profile_picture"));
            objectNode1.put("confirm_code", rs.getString("confirm_code"));
            objectNode1.put("confirm", rs.getBoolean("confirm"));
            objectNode1.put("password", rs.getBoolean("password"));
        }
        catch (SQLException e){
            return null;
        }
        return objectNode1;
    }

    public static ObjectNode getProfile(String email) {
        ObjectNode objectNode1 = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * from user_info WHERE email = " + "\"" + email + "\"";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                objectNode1 = User.toUserJSON(rs);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return  null;
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

        return objectNode1;
    }

    public static ObjectNode getProfile(int id) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * from user_info WHERE userID = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                objectNode1 = User.toUserJSON(rs);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
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
                return null;
            }//end finally try
        }//end try

        return objectNode1;
    }

    public static boolean exist(int id) {
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user_info WHERE userID = " + id;
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                returnValue = false;
            } else {
                returnValue = true;
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
        return returnValue;
    }

    public static void confirmAccount(String email){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE user_info SET confirm = true WHERE email = " + "\"" + email + "\"";
            stmt.executeUpdate(sql);
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

    }

    public static ObjectNode getFriendList(int id) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT userID, user_name, phone_number, DOB, profile_picture, email, favorite FROM user_info, friend "
                    + "WHERE user_info.userID = friend.userID_2 "
                    + "AND userID_1 = " + id
                    + " ORDER BY favorite DESC, user_name";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                ObjectNode friendNode = mapper.createObjectNode();
                friendNode.put("userID", rs.getInt("userID"));
                friendNode.put("user_name", rs.getString("user_name"));
                friendNode.put("email", rs.getString("email"));
                friendNode.put("phone_number", rs.getString("phone_number"));
                friendNode.put("DOB", rs.getString("DOB"));
                friendNode.put("profile_picture", rs.getString("profile_picture"));
                friendNode.put("favorite", rs.getBoolean("favorite"));
                arrayNode.add(friendNode);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
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
        objectNode1.put("friend_list", arrayNode);
        return objectNode1;
    }

    public static ObjectNode searchFriend(int id, String friend_name) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT userID, user_name, phone_number, DOB, profile_picture, email, favorite FROM user_info, friend "
                    + "WHERE user_info.userID = friend.userID_2 "
                    + "AND (user_name LIKE \"%" + friend_name +  "%\" "
                    + "OR email LIKE \"%" + friend_name + "%\") "
                    + "AND userID_1 = " + id
                    + " ORDER BY favorite DESC, user_name";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ObjectNode friendNode = mapper.createObjectNode();
                friendNode.put("userID", rs.getInt("userID"));
                friendNode.put("user_name", rs.getString("user_name"));
                friendNode.put("email", rs.getString("email"));
                friendNode.put("phone_number", rs.getString("phone_number"));
                friendNode.put("DOB", rs.getString("DOB"));
                friendNode.put("profile_picture", rs.getString("profile_picture"));
                friendNode.put("favorite", rs.getBoolean("favorite"));
                arrayNode.add(friendNode);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
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
        objectNode1.put("friend_list", arrayNode);
        return objectNode1;
    }

    public static boolean checkFriend(int userID_1, int userID_2){
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM friend "
                    + "WHERE userID_1 = " + userID_1
                    + " AND userID_2 = " + userID_2;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
                returnValue =  true;
            else returnValue = false;
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

    public static ObjectNode getFriendProfile(int userID, int friendID) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT userID, user_name, phone_number, DOB, profile_picture, email, favorite FROM user_info, friend "
                    + "WHERE user_info.userID = friend.userID_2 "
                    + "AND userID_1 = " + userID
                    + " AND userID_2 = " + friendID;

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                objectNode1.put("userID", rs.getInt("userID"));
                objectNode1.put("user_name", rs.getString("user_name"));
                objectNode1.put("email", rs.getString("email"));
                objectNode1.put("phone_number", rs.getString("phone_number"));
                objectNode1.put("DOB", rs.getString("DOB"));
                objectNode1.put("profile_picture", rs.getString("profile_picture"));
                objectNode1.put("favorite", rs.getBoolean("favorite"));
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
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
        return objectNode1;
    }

    public static boolean addFavorite(int userID, int friendID){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE friend "
                    +"SET favorite = 1 "
                    + "WHERE userID_1 = ?"
                    + " AND userID_2 = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, friendID);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return true;
    }

    public static boolean removeFavorite(int userID, int friendID){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE friend "
                    +"SET favorite = 0 "
                    + "WHERE userID_1 = ?"
                    + " AND userID_2 = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, friendID);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return true;
    }

    public static boolean removeFriend(int userID_1, int userID_2){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "DELETE FROM friend "
                    + "WHERE (userID_1 = ? AND userID_2 = ?) OR (userID_1 = ? AND userID_2 = ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID_1);
            pstmt.setInt(2, userID_2);
            pstmt.setInt(3, userID_2);
            pstmt.setInt(4, userID_1);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return true;
    }

    public static boolean addFriendRequest(int from_userID,int to_userID){
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM friend_request WHERE (from_userID = ? AND to_userID = ?) " +
                    "OR (from_userID = ? AND to_userID = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //check sending request condition
            pstmt.setInt(1, to_userID);
            pstmt.setInt(2, from_userID);
            pstmt.setInt(3, from_userID);
            pstmt.setInt(4, to_userID);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                returnValue = true;
            else{
                sql = "INSERT INTO friend_request VALUES(?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                //get currentTime
                java.util.Date dt = new java.util.Date();
                java.text.SimpleDateFormat sdf =
                        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);

                pstmt.setInt(1, from_userID);
                pstmt.setInt(2, to_userID);
                pstmt.setString(3, currentTime);
                pstmt.executeUpdate();
                returnValue = true;
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            returnValue = false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            returnValue = false;
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

    public static boolean updateNewPassword(String email, String password){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE user_info "
                    +"SET password = ?"
                    + "WHERE email = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return true;
    }

    public static ObjectNode getRequestList(int id) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT userID, user_name, phone_number, DOB, profile_picture, email , timestamp FROM user_info, friend_request "
                    + "WHERE user_info.userID = friend_request.from_userID "
                    + "AND friend_request.to_userID = " + id
                    + " ORDER BY timestamp DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                ObjectNode friendNode = mapper.createObjectNode();
                friendNode.put("userID", rs.getInt("userID"));
                friendNode.put("user_name", rs.getString("user_name"));
                friendNode.put("email", rs.getString("email"));
                friendNode.put("phone_number", rs.getString("phone_number"));
                friendNode.put("DOB", rs.getString("DOB"));
                friendNode.put("profile_picture", rs.getString("profile_picture"));
                arrayNode.add(friendNode);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
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
        objectNode1.put("request_list", arrayNode);
        return objectNode1;
    }

    public static boolean removeFriendRequest(int userID, int friendID){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "DELETE FROM friend_request "
                    + "WHERE from_userID = ?"
                    + " AND to_userID = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, friendID);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return true;
    }

    public static boolean acceptFriendRequest(int userID, int friendID){
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "DELETE FROM friend_request "
                    + "WHERE from_userID = ?"
                    + " AND to_userID = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, friendID);
            pstmt.setInt(2, userID);
            if(pstmt.executeUpdate() != 0){                 //no rows affected
                sql = "INSERT INTO friend VALUES(?, ?, ?), (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userID);
                pstmt.setInt(2, friendID);
                pstmt.setInt(3, 0);
                pstmt.setInt(4, friendID);
                pstmt.setInt(5, userID);
                pstmt.setInt(6, 0);
                pstmt.executeUpdate();
            }
            conn.commit();
            returnValue = true;
        } catch (SQLException se) {
            //Handle errors for JDBC
            try{
                conn.rollback();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            se.printStackTrace();
            returnValue = false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            returnValue = false;
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

    public static ObjectNode searchUser(int id, String user_name) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnNode = mapper.createObjectNode();
        ArrayNode userArrayNode = mapper.createArrayNode();

        Connection conn = null;
        Statement stmt = null;
        boolean flag = false;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            //Select all users
            String sql = "SELECT userID, user_name, email, phone_number, DOB, profile_picture FROM user_info "
                    + "WHERE userID != " + id
                    + " AND (user_name LIKE \"%" + user_name +  "%\" "
                    + "OR email LIKE \"%" + user_name + "%\") ORDER BY user_name";
            PreparedStatement pstmt;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ObjectNode userNode = mapper.createObjectNode();
                userNode.put("userID", rs.getInt("userID"));
                userNode.put("user_name", rs.getString("user_name"));
                userNode.put("email", rs.getString("email"));
                userNode.put("phone_number", rs.getString("phone_number"));
                userNode.put("DOB", rs.getString("DOB"));
                userNode.put("profile_picture", rs.getString("profile_picture"));

                flag = false;
                //check friend_condition
                sql = "Select userID_1 FROM friend WHERE userID_1 = ? AND userID_2 = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setInt(2, rs.getInt("userID"));
                if(pstmt.executeQuery().next()) {
                    userNode.put("relationship_code", 1);                 //friend
                    flag = true;
                }

                if(!flag){                                                               //check sending request
                    sql = "Select * FROM friend_request WHERE from_userID = ? AND to_userID = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    pstmt.setInt(2, rs.getInt("userID"));
                    if(pstmt.executeQuery().next()) {
                        userNode.put("relationship_code", 2);                  //sending request
                        flag = true;
                    }
                }

                if(!flag){                                                               //check receive request
                    sql = "Select * FROM friend_request WHERE from_userID = ? AND to_userID = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, rs.getInt("userID"));
                    pstmt.setInt(2, id);
                    if(pstmt.executeQuery().next()) {
                        userNode.put("relationship_code", 3);                  //receive request
                        flag = true;
                    }
                }

                if(!flag)
                    userNode.put("relationship_code", 0);                 //no relationship

                userArrayNode.add(userNode);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
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
        returnNode.put("user_list", userArrayNode);
        return returnNode;
    }

    public static boolean editProfile(int userID, String userName, String DOB, String phoneNumber){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE user_info "
                    + "SET user_name = ?, DOB = ?, phone_number = ? "
                    + "WHERE userID = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, DOB);
            pstmt.setString(3, phoneNumber);
            pstmt.setInt(4, userID);

            pstmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return true;
    }

    public static boolean uploadProfilePicture(int userID, StringBuilder buffer){
        //save file
        try {
            BufferedImage image = null;
            byte[] imageByte;

            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(buffer.toString().split(",")[1]);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();

            // write the image to a file
            File outputfile = new File("/home/hungphan/Data/Java Workspace/SE Project/master/SEProject/out/artifacts/SEProject_war_exploded/image/profile/" + userID);
            ImageIO.write(image, "png", outputfile);
        }
        catch (Exception e){
            return false;
        }

        //update database
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE user_info "
                    + "SET profile_picture = ? "
                    + "WHERE userID = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "/image/profile/" + userID);
            pstmt.setInt(2, userID);

            pstmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
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
        return true;

    }


}
