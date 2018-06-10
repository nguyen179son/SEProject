

import Helper.JWTHandler;
import Model.ChatRoom;
import Model.Message;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import javax.websocket.Session;
import java.sql.Timestamp;

@ServerEndpoint("/websocket")
public class ChatServer {
    //key: roomID, value: user session in the room
    static private Map<Integer, HashSet<Session>> sessionMap = new HashMap<Integer, HashSet<Session>>();

    @OnOpen
    public void open(@PathParam("token") String token, Session session) throws IOException, EncodeException {
        //add session to session Map
        int userID = JWTHandler.verifyToken(token);
        if(userID < 0) return;
        session.getBasicRemote().sendText("Your userID: " + userID + ", Your session ID:" + session.getId());

        ArrayNode roomIDList = ChatRoom.getChatRoomLIDist(userID);
        session.getUserProperties().put("userID", userID);
        for(int i = 0; i < roomIDList.size(); i ++){
            int roomID = roomIDList.get(i).asInt();
            HashSet<Session> sessions = sessionMap.get(roomID);
            if(sessions == null) {
                sessionMap.put(roomID, new HashSet<Session>());
                sessions = sessionMap.get(roomID);
            }
            sessions.add(session);
        }
    }

    @OnClose
    public void close(Session session) throws IOException, EncodeException {
        //remove session fom session Map
        for (HashSet<Session> sessionList : sessionMap.values()){
            sessionList.remove(session);
        }
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
        //send message to all user in the room but the sender
        ObjectMapper mapper = new ObjectMapper();
        JsonNode messageJSON = mapper.readTree(message);
        ObjectNode returnMessageJSON = mapper.createObjectNode();
        Timestamp sendingTime = new Timestamp(System.currentTimeMillis());
        System.out.println(sendingTime);

        returnMessageJSON.put("roomID", messageJSON.get("roomID").asInt());
        returnMessageJSON.put("from_userID", (Integer) session.getUserProperties().get("userID"));
        returnMessageJSON.put("message", messageJSON.get("message").textValue());
        returnMessageJSON.put("sending_time", sendingTime.toString());
        HashSet<Session> sessionList = sessionMap.get((messageJSON.get("roomID").asInt()));

        for(Session userSession : sessionList){
            if (!userSession.getId().equals(session.getId()))
                userSession.getBasicRemote().sendText(returnMessageJSON.toString());
        }

        Message newMessage = new Message(messageJSON.get("message").textValue(), (Integer) session.getUserProperties().get("userID"), sendingTime);
        ChatRoom.addMessage(messageJSON.get("roomID").asInt(), newMessage);

    }
}
