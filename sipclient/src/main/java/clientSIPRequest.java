import Common.Utils;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/clientSip")
public class clientSIPRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/SipResponse.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String sipToken = req.getParameter("sipToken");
        final String clientID = req.getParameter("clientID");
        final String tokenEndpoint = req.getParameter("tokenEndpoint");
        final String clientSecret = req.getParameter("clientSecret");
        final String redirectURL = req.getParameter("redirectURL");


        final String grantType = "sip_token";

        final String input = format("sip_token=%s&grant_type=%s&client_id=%s&redirect_url=%s&client_secret=%s",
                sipToken,
                grantType,
                clientID,
                redirectURL,
                clientSecret);

        JsonObject post = Utils.getJsonResponseFromURL(tokenEndpoint, input, "POST");

        Utils.setSipResponse(post.toString());

        req.getRequestDispatcher("/jsp/SipResponse.jsp").forward(req, resp);
    }
}
