package Control.Introspection;

import Common.Exceptions.FrameworkCheckedException;
import Common.Exceptions.FrameworkUncheckedException;
import Models.OpenIDConnectObject;
import Models.TokenObject;
import Models.TokenObjectForSIPGrant;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import storage.TokenStorage;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static Common.FwUtils.getCredentialFromBearerHeader;

@WebServlet(urlPatterns = "/validations/userinfo")
public class UserInfoEndpoint extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // We get already authenticated request
        final String accessToken;
        try {
            accessToken = getCredentialFromBearerHeader(req.getHeader("Authorization"));
        } catch (FrameworkCheckedException e) {
            throw new FrameworkUncheckedException("Invalid access token", e);
        }

        final JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        final TokenObject tokenObject;
        try {
            tokenObject = TokenStorage.getTokenByAccessCode(accessToken);
        } catch (FrameworkCheckedException e) {
            throw new FrameworkUncheckedException("Invalid access token", e);
        }

        if (tokenObject instanceof OpenIDConnectObject) {
            final OpenIDConnectObject openIDConnectObject = (OpenIDConnectObject) tokenObject;
            final JWTClaimsSet jwtClaimsSet;
            try {
                final SignedJWT signedJWT = SignedJWT.parse(openIDConnectObject.getIdToken());
                jwtClaimsSet = signedJWT.getJWTClaimsSet();
            } catch (ParseException e) {
                throw new FrameworkUncheckedException("Internal error", e);
            }

            jsonObjectBuilder.add("sub", (String) jwtClaimsSet.getClaim("sub"));
            jsonObjectBuilder.add("email", (String) jwtClaimsSet.getClaim("email"));
            jsonObjectBuilder.add("age", (Long) jwtClaimsSet.getClaim("age"));
        } else if (tokenObject instanceof TokenObjectForSIPGrant) {
            final TokenObjectForSIPGrant tokenObjectForSIPGrant = (TokenObjectForSIPGrant) tokenObject;

            final Map<String, Object> sipClaims = tokenObjectForSIPGrant.getSipClaims();


            jsonObjectBuilder.add("sub", (String) sipClaims.get("sub"));
            jsonObjectBuilder.add("email", (String) sipClaims.get("email"));
            jsonObjectBuilder.add("age", (Long) sipClaims.get("age"));
        }

        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().print(jsonObjectBuilder.build().toString());
    }
}
