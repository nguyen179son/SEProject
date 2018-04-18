package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Helper.validation;
@WebServlet(name = "editProfile")
public class editProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("id"));
        String nick_name = request.getParameter("nick_name");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("nwqPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        String phone = request.getParameter("phone");
        String DoB = request.getParameter("DoB");

        String validationResult = validation.UserUpdateValidation(nick_name,email,oldPassword,newPassword,confirmNewPassword,DoB);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id",id);
        request.getRequestDispatcher("edit-profile.jsp").forward(request,response);
    }
}
