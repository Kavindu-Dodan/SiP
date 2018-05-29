package Control.Admin;

import Common.Exceptions.FrameworkUncheckedException;
import Common.FwUtils;
import storage.SipUserStorage;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/ListSIPUsers")
public class ListSIPProvisionedUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();

        FwUtils.addHtmlHeaderWithTitle(
                outputStream,
                "Users received through SIP tokens",
                Arrays.asList("Username", "Email", "Age", "Issuer"));

        SipUserStorage.getSipUsers().forEach(
                sipUser -> {
                    try {
                        outputStream.println("<tr>");

                        outputStream.println(format("<td>%s</td>", sipUser.getUsername()));
                        outputStream.println(format("<td>%s</td>", sipUser.getEmail()));
                        outputStream.println(format("<td>%s</td>", sipUser.getAge()));
                        outputStream.println(format("<td>%s</td>", sipUser.getIssuer()));

                        outputStream.println("</tr>");

                    } catch (IOException e) {
                        throw new FrameworkUncheckedException("Error while writing to outputstream");
                    }
                }
        );

        FwUtils.addHtmlFooter(outputStream);
    }
}
