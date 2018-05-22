package Control.Admin;

import Common.Exceptions.FrameworkUncheckedException;
import Common.FwUtils;
import storage.EndUsers;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/ListUsers")
public class ListUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();

        FwUtils.addHtmlHeaderWithTitle(
                outputStream,
                "Registered end users",
                Arrays.asList("Username", "Email", "Age"));


        EndUsers.getUsers().forEach(
                user -> {
                    try {
                        outputStream.println("<tr>");

                        outputStream.println(format("<td>%s</td>", user.getUsername()));
                        outputStream.println(format("<td>%s</td>", user.getEmail()));
                        outputStream.println(format("<td>%s</td>", user.getAge()));

                        outputStream.println("</tr>");

                    } catch (IOException e) {
                        throw new FrameworkUncheckedException("Error while writing to outputstream");
                    }
                }
        );

        FwUtils.addHtmlFooter(outputStream);
    }
}
