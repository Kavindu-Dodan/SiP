package Common;

import Common.Exceptions.FrameworkCheckedException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Map;

public class JWTCreator {

    private JWTCreator() {
    }

    public static SignedJWT createJWT(final Map<String, Object> claims) throws FrameworkCheckedException {
        final JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256).build();

        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

        claims.keySet().forEach(
                key -> builder.claim(key, claims.get(key))
        );

        final JWTClaimsSet jwtClaimsSet = builder.build();

        final RSASSASigner rsassaSigner = new RSASSASigner(CertificateLoader.getCertificateLoader().getPrivateKey());

        final SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        try {
            signedJWT.sign(rsassaSigner);
        } catch (JOSEException e) {
            throw new FrameworkCheckedException("Error while creating the token", e);
        }


        return signedJWT;
    }
}
