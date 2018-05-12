package Control;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/.well-known/openid-configuration")
public class DiscoveryDocument extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();

        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("jwks_uri", "/openid-configuration/jwks_uri");
        outputStream.print(objectBuilder.build().toString());
    }
}
