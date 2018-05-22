package Control;

import Common.Constants;

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

        final int localPort = req.getLocalPort();
        final String remoteHost = req.getRemoteHost();

        final String hostPort = "http://" + remoteHost + ":" + localPort;

        final String issuer = hostPort + Constants.getContextRoot();

        objectBuilder.add("issuer", issuer);
        objectBuilder.add("jwks_uri", issuer + "/openid-configuration/jwks_uri");
        objectBuilder.add("introspection_uri", issuer + "/validations/token/introspect");
        outputStream.print(objectBuilder.build().toString());
    }
}
