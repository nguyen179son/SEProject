package Controller.registration;

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

@WebServlet(name = "check-confirmation-code")
public class ConfirmationCode extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String email = request.getParameter("email");
        String confirmationCode = request.getParameter("confirmation_code");
        System.out.println(request.getParameter("email"));
        System.out.println(request.getParameter("confirmation_code"));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();                 //return data

        if (Validation.checkConfirmationCode(email, confirmationCode)) {
            User.confirmAccount(email);                                        //update database
            ObjectNode userInfoJson = User.getProfile(email);        //user_infor
            //generate token
            String token = JWTHandler.generateToken(userInfoJson.get("userID").toString(), 7);
            returnJSON.put("success", true);
            returnJSON.put("token", token);
            returnJSON.put("userID", userInfoJson.get("userID").asInt());
            returnJSON.put("profile_picture", userInfoJson.get("profile_picture").textValue());
        } else {
            returnJSON.put("success", false);
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
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        System.out.println(email);
        request.getRequestDispatcher("confirmcode.jsp").forward(request, response);
    }
}
