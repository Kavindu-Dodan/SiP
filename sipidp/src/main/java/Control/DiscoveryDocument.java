package Control;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Common.Constants.getAuthorizarionEndpoint;
import static Common.Constants.getThisIssuer;
import static Common.Constants.getTokenEndpoint;

@WebServlet(urlPatterns = "/.well-known/openid-configuration")
public class DiscoveryDocument extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final ServletOutputStream outputStream = resp.getOutputStream();

        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("issuer", getThisIssuer());
        objectBuilder.add("authorization_endpoint", getThisIssuer() + getAuthorizarionEndpoint());
        objectBuilder.add("token_endpoint", getThisIssuer() + getTokenEndpoint());
        objectBuilder.add("jwks_uri", getThisIssuer() + "/openid-configuration/jwks_uri");
        objectBuilder.add("introspection_uri", getThisIssuer() + "/validations/token/introspect");
        objectBuilder.add("user_info_endpoint", getThisIssuer() + "/validations/userinfo");
        outputStream.print(objectBuilder.build().toString());
    }
}
