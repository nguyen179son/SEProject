package Controller;

import Helper.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login")
public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");

        if (Validation.UserLoginValidation(user_name, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user_name", user_name);
            response.sendRedirect("/SEProject_war_exploded/");
        } else {
            request.setAttribute("error_message", "Wrong email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }
    }
}
