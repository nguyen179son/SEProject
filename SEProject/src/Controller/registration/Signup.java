package Controller.registration;

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

@WebServlet(name = "signup")
public class Signup extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String nickname = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();                 //return data
        returnJSON.put("success", true);

        ObjectNode validationResult = Validation.registerValidation(nickname, email, password, confirm_password);
        if (validationResult.get("valid").asBoolean()) {
            User user = new User(email, password, nickname);
            if(user.save()){
                returnJSON.put("valid", true);
            }
            else
                returnJSON.put("success", false);              //internal error from server
        } else {
            returnJSON.put("valid", false);
            returnJSON.put("error_message", validationResult.get("error_message"));
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
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}
