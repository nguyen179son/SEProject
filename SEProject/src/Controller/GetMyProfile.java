package Controller;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getParameter("token");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();                 //return data
        int userID = JWTHandler.verifyToken(token);

        if (userID < 0) {                                                 //verifying token fails
            objectNode1.put("verify_token", false);
            objectNode1.put("verify_value", userID);

        }
        else {
            ObjectNode userInfoJson = User.getProfile(userID);              //user_infor
            objectNode1.put("verify_token", true);
            objectNode1.put("userID", userID);
            objectNode1.put("user_name", userInfoJson.get("user_name").textValue());
            objectNode1.put("email", userInfoJson.get("email").textValue());
            objectNode1.put("phone_number", userInfoJson.get("phone_number").textValue());
            objectNode1.put("DOB", userInfoJson.get("DOB").textValue());
            objectNode1.put("profile_picture", userInfoJson.get("profile_picture").textValue());
        }

        PrintWriter wr = response.getWriter();
        wr.write(objectNode1.toString());
        wr.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}
