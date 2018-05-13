package Common;

import Common.Exceptions.FrameworkUncheckedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class CertificateLoader {

    private final X509Certificate x509Certificate;
    private final PrivateKey privateKey;

    private static CertificateLoader certificateLoader = null;

    private CertificateLoader() {
        try {
            x509Certificate = loadX509Certificate();
            privateKey = loadPrivateKey();
        } catch (IOException | CertificateException e) {
            throw new FrameworkUncheckedException("Error while loading certificate");
        }
    }

    public static synchronized CertificateLoader getCertificateLoader() {
        if (certificateLoader == null) {
            certificateLoader = new CertificateLoader();
        }

        return certificateLoader;
    }

    public X509Certificate getX509Certificate() {
        return x509Certificate;
    }

    private X509Certificate loadX509Certificate() throws CertificateException, IOException {
        loadPrivateKey();
        final InputStream resourceAsStream = getClass().getResourceAsStream("/certificates/certificate.crt");
        final CertificateFactory x509 = CertificateFactory.getInstance("X509");
        return (X509Certificate) x509.generateCertificate(resourceAsStream);
    }

    private PrivateKey loadPrivateKey() throws IOException {
        final InputStream resourceAsStream = getClass().getResourceAsStream("/certificates/private_key.der");

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(64564);

        int read;
        while ((read = resourceAsStream.read()) != -1) {
            byteArrayOutputStream.write(read);
        }

        final byte[] bytes = byteArrayOutputStream.toByteArray();

        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(bytes);

        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(encodedKeySpec);

        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new FrameworkUncheckedException("Error", e);
        }
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
