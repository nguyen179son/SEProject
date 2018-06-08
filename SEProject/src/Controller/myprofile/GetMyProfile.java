package Controller.myprofile;

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

@WebServlet(name = "get-my-profile")
public class GetMyProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getParameter("token");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();                 //return data
        int userID = JWTHandler.verifyToken(token);

        if (userID < 0) {
            if(userID == -1 || userID == -2) {                              //verifying token fails
                returnJSON.put("verify_token", false);
            }
            else {                                                          //internal error from server
                returnJSON.put("verify_token", true);
                returnJSON.put("success", false);
            }
        }
        else {
            returnJSON.put("verify_token", true);
            returnJSON.put("success", true);
            ObjectNode userInfoJson = User.getProfile(userID);              //user_infor
            if (userInfoJson == null)                                       //internal error from server
                returnJSON.put("success", false);
            else {
                returnJSON.put("userID", userID);
                returnJSON.put("user_name", userInfoJson.get("user_name").textValue());
                returnJSON.put("email", userInfoJson.get("email").textValue());
                returnJSON.put("phone_number", userInfoJson.get("phone_number").textValue());
                returnJSON.put("DOB", userInfoJson.get("DOB").textValue());
                returnJSON.put("profile_picture", userInfoJson.get("profile_picture").textValue());
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
        request.getRequestDispatcher("profile.jsp").forward(request,response);
    }
}
