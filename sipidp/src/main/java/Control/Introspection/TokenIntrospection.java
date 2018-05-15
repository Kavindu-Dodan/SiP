package Control.Introspection;

import storage.TokenStorage;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/validations/token/introspect")
public class TokenIntrospection extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // We get already authenticated request
        final String token = req.getParameter("token");

        final JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        if (TokenStorage.verifyAccessToken(token)) {
            jsonObjectBuilder.add("active", true);
        } else {
            jsonObjectBuilder.add("active", false);
        }

        resp.getOutputStream().print(jsonObjectBuilder.build().toString());
    }
}
