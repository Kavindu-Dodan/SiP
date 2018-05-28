import Common.Constants;
import Common.Utils;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/clientToken")
public class ClientTokenRequest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String authCode = req.getParameter("authCode");
        final String clientID = req.getParameter("clientID");
        final String tokenEndpoint = req.getParameter("tokenEndpoint");
        final String clientSecret = req.getParameter("clientSecret");

        final String grantType = "authorization_code";

        final String input = format("code=%s&grant_type=%s&client_id=%s&redirect_url=%s&client_secret=%s",
                authCode,
                grantType,
                clientID,
                Constants.getMyContextPath() + Constants.getRedirectEndpoint(),
                clientSecret);

        JsonObject post = Utils.getJsonResponseFromURL(tokenEndpoint, input, "POST");

        resp.getOutputStream().println(post.toString());
    }
}
