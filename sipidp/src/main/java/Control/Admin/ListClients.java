package Control.Admin;

import Common.Exceptions.FrameworkUncheckedException;
import storage.IDPClients;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/ListClients")
public class ListClients extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();
        IDPClients.getClients().forEach(
                client -> {
                    try {
                        outputStream.println(
                                format("Client name: %s  Client id: %s Redirect URL: %s",
                                        client.getClientName(),
                                        client.getClientId(),
                                        client.getRedirectUrl()));
                    } catch (IOException e) {
                        throw new FrameworkUncheckedException("Error while writing to outputstream");
                    }
                }
        );
    }
}
