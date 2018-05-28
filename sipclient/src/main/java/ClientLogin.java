import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/clientLogin")
public class ClientLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/clientLogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String authorizationEndpoint = req.getParameter("authorizationEndpoint");
        final String clientID = req.getParameter("clientID");
        final String redirectURL = req.getParameter("redirectURL");

        final String scope = "openid identity_share";
        final String responseType = "code";

        resp.sendRedirect(format(
                "%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s",
                authorizationEndpoint,
                clientID,
                redirectURL,
                scope,
                responseType));
    }
}
