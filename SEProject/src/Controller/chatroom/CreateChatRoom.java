package Controller.chatroom;

import Helper.JWTHandler;
import Helper.Validation;
import Model.ChatRoom;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "create-chat-room")
public class CreateChatRoom extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getParameter("token");
        String[] userIDListParam = request.getParameterValues("userID_list[]");

        int[] userIDListInteger = new int[userIDListParam.length];
        for(int i = 0; i < userIDListInteger.length; i ++) {
            userIDListInteger[i] = Integer.parseInt(userIDListParam[i]);
        }


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();                 //return data
        int userID = JWTHandler.verifyToken(token);

        if (userID < 0) {
            returnJSON.put("verify_token", false);
        }
        else {
            returnJSON.put("verify_token", true);
            returnJSON.put("success", true);
            int roomID = ChatRoom.createChatRoom(userID, userIDListInteger);
            if(roomID < 0)
                returnJSON.put("success", false);          //internal error from server
            else{
                returnJSON.put("roomID", roomID);
            }
        }

        PrintWriter wr = response.getWriter();
        wr.write(returnJSON.toString());
        wr.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}

