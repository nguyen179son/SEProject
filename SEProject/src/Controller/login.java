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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");

        if (Validation.UserLoginValidation(user_name, password)) {
            //getSession to take redirect_url
            HttpSession session = request.getSession();


            //generate return json object
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode userInfoJson = User.getProfile(user_name);        //user_infor
            ObjectNode objectNode1 = mapper.createObjectNode();                 //return data
            //generate token
            String token = JWTHandler.generateToken(userInfoJson.get("userID").toString(), 7);

            objectNode1.put("success", true);
            objectNode1.put("confirm", userInfoJson.get("confirm").booleanValue());
            //user already confirmed
            if (userInfoJson.get("confirm").booleanValue()) {
                objectNode1.put("token", token);
                objectNode1.put("redirect_url", session.getAttribute("returnUrl") == null || session.getAttribute("returnUrl").toString().equals("") ?
                        "/SEProject_war_exploded/" : session.getAttribute("returnUrl").toString());
            }
            else {  //user haven't confirmed yet
                objectNode1.put("redirect_url", "/SEProject_war_exploded/confirmcode.jsp");
            }
            PrintWriter wr = response.getWriter();
            wr.write(objectNode1.toString());
            wr.flush();
        } else {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode1 = mapper.createObjectNode();
            objectNode1.put("success", false);
            PrintWriter wr = response.getWriter();
            wr.write(objectNode1.toString());
            wr.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
