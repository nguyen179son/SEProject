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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String nickname = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();                 //return data

        String validation_result = Validation.UserRegisterValidation(nickname, email, password, confirm_password);
        if (validation_result.equals("")) {
            User user = new User(email, password, nickname);
            user.save();
            objectNode1.put("success", true);
        } else {
            objectNode1.put("success", false);
            objectNode1.put("error_message", validation_result);
        }

        PrintWriter wr = response.getWriter();
        wr.write(objectNode1.toString());
        wr.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}
