package Control.Admin;

import Models.IdentityProvider;
import storage.IdentityProviders;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/addProvider")
public class AddProvider extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/addProvider.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("providerName");
        final String url = req.getParameter("providerURL");
        final String discoveryURL = req.getParameter("discoveryURL");


        IdentityProviders.addProvider(new IdentityProvider(name, url, discoveryURL));

        ServletOutputStream outputStream = resp.getOutputStream();

        outputStream.println("Identity provider added successfully");
    }
}
