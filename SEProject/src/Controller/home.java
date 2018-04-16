package Controller;

import Helper.validation;
import Model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "home")
public class home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        if (validation.UserLoginValidation(user_name, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userID", User.getID(user_name));
            response.sendRedirect(session.getAttribute("returnUrl") == null || session.getAttribute("returnUrl").toString().equals("") ?
                    "/SEProject_war_exploded/" : session.getAttribute("returnUrl").toString());
        } else {
            request.setAttribute("error_message", "Wrong email or password");
            request.setAttribute("user_name", user_name);
            request.setAttribute("password", password);
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("userID") == null || session.isNew()) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            response.sendRedirect("/SEProject_war_exploded/home");
        }
    }
}
