package Control.Admin;

import Common.Exceptions.FrameworkUncheckedException;
import Common.FwUtils;
import storage.IdentityProviders;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/ListProviders")
public class ListProviders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();

        FwUtils.addHtmlHeaderWithTitle(
                outputStream,
                "Registered Identity providers",
                Arrays.asList("Provider name", "URL", "Discovery URl"));

        IdentityProviders.getProviderList().forEach(
                provider -> {
                    try {

                        outputStream.println("<tr>");

                        outputStream.println(format("<td>%s</td>", provider.getProviderName()));
                        outputStream.println(format("<td>%s</td>", provider.getProviderUrl()));
                        outputStream.println(format("<td>%s</td>", provider.getProviderDiscovery()));

                        outputStream.println("</tr>");

                    } catch (IOException e) {
                        throw new FrameworkUncheckedException("Error while writing to outputstream");
                    }
                }
        );

        FwUtils.addHtmlFooter(outputStream);
    }
}
