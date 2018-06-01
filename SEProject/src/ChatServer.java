

import Helper.JWTHandler;
import Model.ChatRoom;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import javax.websocket.Session;

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

        for(int i = 0; i < roomIDList.size(); i ++){
            int roomID = roomIDList.get(i).asInt();
            System.out.println(roomID);
            HashSet<Session> sessions = sessionMap.get(roomID);
            if(sessions == null) {
                sessionMap.put(roomID, new HashSet<Session>());
                sessions = sessionMap.get(roomID);
            }
            sessions.add(session);
        }

        for (HashSet<Session> sessionList : sessionMap.values()){
            for(Session mySession : sessionList)
                System.out.println(mySession.getId());
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
        HashSet<Session> sessionList = sessionMap.get((messageJSON.get("roomID").asInt()));
        int i = 0;
        for(Session userSession : sessionList){
            System.out.println("i = " + i++);
            //if (!userSession.getId().equals(session.getId()))
                userSession.getBasicRemote().sendText("Room " + messageJSON.get("roomID") + ": " +messageJSON.get("message").textValue());
        }

    }
}
