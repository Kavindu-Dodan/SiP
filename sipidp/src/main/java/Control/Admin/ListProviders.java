package Control.Admin;

import Common.Exceptions.FrameworkUncheckedException;
import storage.IdentityProviders;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/ListProviders")
public class ListProviders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();
        IdentityProviders.getProviderList().forEach(
                provider -> {
                    try {
                        outputStream.println(
                                format("Provider name: %s  Provider URL: %s Discovery URL: %s",
                                        provider.getProviderName(),
                                        provider.getProviderUrl(),
                                        provider.getProviderDiscovery()));
                    } catch (IOException e) {
                        throw new FrameworkUncheckedException("Error while writing to outputstream");
                    }
                }
        );
    }
}
