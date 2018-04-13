package Controller;

import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profile")
public class profile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userModel = new User("","","");
        int id = Integer.parseInt(request.getParameter("id"));
        if (userModel.exist(id)) {
            request.setAttribute("id", id);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("404-error.jsp").forward(request,response);
        }
    }
}
