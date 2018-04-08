package Controller;

import Helper.validation;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signup")
public class signup extends HttpServlet {
    validation valid = new validation();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirmpassword");

        String validation_result = valid.UserRegisterValidation(nickname, email, password, confirm_password);
        if (validation_result.equals("")) {
            User user = new User(email, password, nickname);
            user.save();
            response.sendRedirect("/SEProject_war_exploded");
        } else {
            request.setAttribute("error_message", validation_result);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("confirm_password", confirm_password);
            request.setAttribute("nickname", nickname);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}
