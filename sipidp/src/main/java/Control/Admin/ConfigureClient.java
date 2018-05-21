package Control.Admin;

import Common.FwUtils;
import Models.Client;
import Models.User;
import storage.EndUsers;
import storage.IDPClients;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/admin/addClient")
public class ConfigureClient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/addClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("clientName");
        final String redirectUrl = req.getParameter("redirectUrl");

        final String id = FwUtils.getRandomId(10);
        final String password = FwUtils.getRandomId(10);

        IDPClients.addClient(new Client(name, id, password, redirectUrl));

        ServletOutputStream outputStream = resp.getOutputStream();

        outputStream.print("Client added successfully");
        outputStream.print(format("Client identifier : %s", id));
        outputStream.print(format("Client password : %s", password));
    }

}
