package Control;

import Common.CertificateLoader;
import Common.Exceptions.FrameworkUncheckedException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.cert.X509Certificate;

@WebServlet(urlPatterns = "/openid-configuration/jwks_uri")
public class JWKDocument extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final X509Certificate certificate = CertificateLoader.getCertificateLoader().getX509Certificate();

        final JWK jwk;

        try {
            jwk = JWK.parse(certificate);
        } catch (JOSEException e) {
            throw new FrameworkUncheckedException("Error while loading to JWK", e);
        }

        resp.getOutputStream().print(jwk.toJSONString());
    }
}
