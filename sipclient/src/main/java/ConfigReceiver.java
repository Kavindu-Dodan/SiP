import Common.Configurations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/configReceiver")
public class ConfigReceiver extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configurations.setAuthEndpointForIdToken(req.getParameter("authorizationEndpointForOIDC"));
        Configurations.setClientIdForIdToken(req.getParameter("clientIDForOIDC"));
        Configurations.setClientSecretForIdToken(req.getParameter("clientSecretForOIDC"));
        Configurations.setTokenEndpointForIdToken(req.getParameter("tokenEndpointForOIDC"));

        Configurations.setAuthEndpointForIdSip(req.getParameter("authorizationEndpointForSIP"));
        Configurations.setClientIdForIdSip(req.getParameter("clientIDForSiP"));
        Configurations.setClientSecretForIdSip(req.getParameter("clientSecretForSiP"));
        Configurations.setTokenEndpointForIdSip(req.getParameter("tokenEndpointForSiP"));

        resp.sendRedirect("/sip-client/clientLogin");
    }
}
