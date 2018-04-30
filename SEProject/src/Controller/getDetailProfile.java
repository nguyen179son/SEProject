package Controller;

import Model.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getDetailProfile")
public class getDetailProfile extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userModel = new User("", "", "");
        int id = Integer.parseInt(request.getParameter("id"));
        String result = userModel.getProfileByID(id);
        response.setContentType("text/plain");
        response.getWriter().write(result);

    }
}
