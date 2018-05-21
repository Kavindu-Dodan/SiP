package Control.Admin;

import Models.User;
import storage.EndUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/createUser")
public class CreateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/createUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        final int age = Integer.parseInt(req.getParameter("age"));

        User newUser = new User(username, password);

        newUser.setEmail(email);
        newUser.setAge(age);

        EndUsers.addNewUser(newUser);

        resp.getOutputStream().print("User added successfully");
    }
}
