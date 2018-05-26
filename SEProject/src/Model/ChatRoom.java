package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatRoom {
    public static ObjectNode getChatRoomList(int id) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();
        ArrayNode roomArrayJSON = mapper.createArrayNode();

        Connection conn = null;


        try {
            conn = DatabaseConnection.getConnection();
            Statement st1 = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            String sql = "select CR.roomID, seen, message " +
                    "from chat_room as CR LEFT JOIN message ON CR.roomID = message.roomID " +
                    "where CR.userId = " + id +
                    " and sending_time >= ALL (SELECT sending_time from message as MS where CR.roomID = MS.roomID) " +
                    "ORDER BY sending_time DESC";
            ResultSet rs1 = st1.executeQuery(sql);
            Statement st2 = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            ResultSet rs2 = null;
            while (rs1.next()){
                ObjectNode roomNodeJSON = mapper.createObjectNode();
                ArrayNode userArrayJSON = mapper.createArrayNode();

                sql = "Select chat_room.userID, user_name from chat_room, user_info" +
                        " Where chat_room.userID != " + id +
                        " and chat_room.userID = user_info.userID " +
                        "and roomID = " +  rs1.getInt("roomID");
                rs2 = st2.executeQuery(sql);
                while(rs2.next()) {
                    ObjectNode userNodeJSON = mapper.createObjectNode();
                    userNodeJSON.put("userID", rs2.getInt("userID"));
                    userNodeJSON.put("user_name", rs2.getString("user_name"));
                    userArrayJSON.add(userNodeJSON);
                }

                roomNodeJSON.put("roomID", rs1.getInt("roomID"));
                roomNodeJSON.put("user_list", userArrayJSON);
                roomNodeJSON.put("last_message", rs1.getString("message"));
                roomNodeJSON.put("seen", rs1.getBoolean("seen"));
                roomArrayJSON.add(roomNodeJSON);
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
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        returnJSON.put("room_list", roomArrayJSON);
        return returnJSON;
    }

    public static ObjectNode searchChatRoom(int id, String searchKey) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();
        ArrayNode roomArrayJSON = mapper.createArrayNode();

        Connection conn = null;


        try {
            conn = DatabaseConnection.getConnection();
            Statement st1 = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            String sql = "select CR.roomID, seen, message " +
                    "from chat_room as CR LEFT JOIN message ON CR.roomID = message.roomID " +
                    "where CR.userId = " + id +
                    " and exists (SELECT userID from user_info " +
                    "WHERE userID != "+ id +
                    " AND user_name LIKE \"%" + searchKey + "%\"" +
                    " AND userID IN (SELECT userID from chat_room as CR1 where CR1.roomID = CR.roomID)) " +
                    "and sending_time >= ALL (SELECT sending_time from message as MS where CR.roomID = MS.roomID)" +
                    "ORDER BY sending_time DESC";
            ResultSet rs1 = st1.executeQuery(sql);
            Statement st2 = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            ResultSet rs2 = null;
            while (rs1.next()){
                ObjectNode roomNodeJSON = mapper.createObjectNode();
                ArrayNode userArrayJSON = mapper.createArrayNode();

                sql = "Select chat_room.userID, user_name from chat_room, user_info" +
                        " Where chat_room.userID != " + id +
                        " and chat_room.userID = user_info.userID " +
                        "and roomID = " +  rs1.getInt("roomID");
                rs2 = st2.executeQuery(sql);
                while(rs2.next()) {
                    ObjectNode userNodeJSON = mapper.createObjectNode();
                    userNodeJSON.put("userID", rs2.getInt("userID"));
                    userNodeJSON.put("user_name", rs2.getString("user_name"));
                    userArrayJSON.add(userNodeJSON);
                }

                roomNodeJSON.put("roomID", rs1.getInt("roomID"));
                roomNodeJSON.put("user_list", userArrayJSON);
                roomNodeJSON.put("last_message", rs1.getString("message"));
                roomNodeJSON.put("seen", rs1.getBoolean("seen"));
                roomArrayJSON.add(roomNodeJSON);
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
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        returnJSON.put("room_list", roomArrayJSON);
        return returnJSON;
    }
}
