package Control.Admin;

import Common.Exceptions.FrameworkUncheckedException;
import storage.EndUsers;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/ListUsers")
public class ListUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();

        EndUsers.getUsers().forEach(
                user -> {
                    try {
                        outputStream.println(
                                format("Username: %s  Email: %s  Age: %s",
                                        user.getUsername(),
                                        user.getEmail(),
                                        user.getAge()));
                    } catch (IOException e) {
                        throw new FrameworkUncheckedException("Error while writing to outputstream");
                    }
                }
        );
    }
}
