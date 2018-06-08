package Controller.friend.request;

import Helper.JWTHandler;
import Helper.Validation;
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

@WebServlet(name = "search-user")
public class SearchUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getParameter("token");
        String user_name = request.getParameter("user_name");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();                 //return data
        int userID = JWTHandler.verifyToken(token);

        if (userID < 0) {
            if(userID == -1) {                              //verifying token fails
                returnJSON.put("verify_token", false);
            }
            else {                                                          //internal error from server
                returnJSON.put("verify_token", true);
                returnJSON.put("success", false);
            }
        }
        else {
            returnJSON = User.searchUser(userID, user_name);
            if (returnJSON == null){                                         //internal error from server
                returnJSON = mapper.createObjectNode();
                returnJSON.put("verify_token", true);
                returnJSON.put("success", false);
            }
            else {
                returnJSON.put("verify_token", true);
                returnJSON.put("success", true);
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
