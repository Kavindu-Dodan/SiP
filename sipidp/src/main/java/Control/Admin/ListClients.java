package Control.Admin;

import Common.Exceptions.FrameworkUncheckedException;
import Common.FwUtils;
import storage.Clients;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/ListClients")
public class ListClients extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();

        FwUtils.addHtmlHeaderWithTitle(
                outputStream,
                "Registered client applications",
                Arrays.asList("Client name", "Client ID", "Client redirect URL", "Client secret"));

        Clients.getClients().forEach(
                client -> {
                    try {
                        outputStream.println("<tr>");

                        outputStream.println(format("<td>%s</td>", client.getClientName()));
                        outputStream.println(format("<td>%s</td>", client.getClientId()));
                        outputStream.println(format("<td>%s</td>", client.getRedirectUrl()));
                        outputStream.println(format("<td>%s</td>", client.getClientPassword()));

                        outputStream.println("</tr>");
                    } catch (IOException e) {
                        throw new FrameworkUncheckedException("Error while writing to outputstream");
                    }
                }
        );

        FwUtils.addHtmlFooter(outputStream);
    }
}
