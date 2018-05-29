package Control.Token;

import Common.Exceptions.FrameworkCheckedException;
import Common.FwUtils;
import Models.Client;
import Models.IdentityProvider;
import Models.SipUser;
import Models.TokenObjectForSIPGrant;
import Models.OpenIDConnectObject;
import Models.SharedIdentityObject;
import Models.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import storage.Clients;
import storage.IdentityProviders;
import storage.SipUserStorage;
import storage.TokenStorage;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static Common.Constants.getThisIssuer;
import static Common.FwUtils.getJWKInformation;

public class TokenRequestProcessor {

    private TokenRequestProcessor() {
    }

    public static JsonObject processRequest(final HttpServletRequest request) {
        final Map<String, String[]> parameterMap = request.getParameterMap();

        // Verify client authentication
        final String[] clientId = parameterMap.get("client_id");
        if (clientId == null || clientId.length < 1) {
            return createErrorResponse("Missing client grants");
        }

        final Client client;

        try {
            client = Clients.getClientOnId(clientId[0]);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse("Invalid client grants");
        }

        final String[] redirectUrl = parameterMap.get("redirect_url");
        if (redirectUrl == null || redirectUrl.length < 1) {
            return createErrorResponse("Missing client grants");
        }

        final String[] clientSecret = parameterMap.get("client_secret");
        if (clientSecret == null || clientSecret.length < 1) {
            return createErrorResponse("Missing client grants");
        }

        if (!(client.authenticate(clientSecret[0])
                && client.getRedirectUrl().equals(redirectUrl[0]))) {
            return createErrorResponse("Invalid client grants");
        }


        // Grant type - grant_type
        final String[] grant_types = parameterMap.get("grant_type");
        if (grant_types == null || grant_types.length < 1) {
            return createErrorResponse("Grant type not provided");
        }

        // This is token request
        if ("authorization_code".equals(grant_types[0])) {
            return processAuthorizationCodeTokenRequest(parameterMap);
        } else if (("sip_token").equals(grant_types[0])) {
            return processSIPTokenRequest(parameterMap);
        }

        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Error", "Invalid grant type");

        return objectBuilder.build();
    }

    private static JsonObject processAuthorizationCodeTokenRequest(final Map<String, String[]> parameterMap) {
        // Verify auth code
        final String[] codes = parameterMap.get("code");
        if (codes == null || codes.length < 1) {
            return createErrorResponse("Missing authorization code");
        }

        final String authorizationCode = codes[0];

        // Get token object by auth code
        final OpenIDConnectObject tokenObject;
        try {
            tokenObject = (OpenIDConnectObject) TokenStorage.getByAuthCode(authorizationCode);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Invalid grants");
        }

        // If valid store by access token
        try {
            TokenStorage.addByAccessToken(authorizationCode, tokenObject.getAccessToken(), tokenObject);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Failure to store token");
        }


        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("access_token", tokenObject.getAccessToken());
        objectBuilder.add("token_type", "Bearer");
        objectBuilder.add("id_token", tokenObject.getIdToken());

        if (tokenObject instanceof SharedIdentityObject) {
            final SharedIdentityObject sharedIdentityObject = (SharedIdentityObject) tokenObject;
            objectBuilder.add("shared_token", sharedIdentityObject.getSharedIdentityToken());
        }

        return objectBuilder.build();
    }

    private static JsonObject createErrorResponse(final String message) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Error", message);
        return objectBuilder.build();
    }

    private static JsonObject processSIPTokenRequest(final Map<String, String[]> parameterMap) {
        // Verify SIP token
        final String[] token = parameterMap.get("sip_token");
        if (token == null || token.length < 1) {
            return createErrorResponse("Missing token grant");
        }

        final SignedJWT signedJWT;

        try {
            signedJWT = SignedJWT.parse(token[0]);
        } catch (ParseException e) {
            return createErrorResponse("Invalid token received");
        }

        final JWTClaimsSet jwtClaimsSet;
        try {
            jwtClaimsSet = signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            return createErrorResponse("Invalid token received");
        }

        final Object istClaim = jwtClaimsSet.getClaim("ist");

        if (istClaim == null || !(istClaim instanceof JSONArray)) {
            return createErrorResponse("Invalid token received");
        }

        final JSONArray issuedToList = (JSONArray) istClaim;

        if (!issuedToList.contains(getThisIssuer())) {
            return createErrorResponse("Token grant cannot be accepted");
        }

        // Token is issued to us. Now move on to validations

        // Check issuer for registered value
        final Object iss = jwtClaimsSet.getClaim("iss");

        final IdentityProvider issuedProvider;

        try {
            issuedProvider = IdentityProviders.getIdentityProvider((String) iss);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Issuer is not recognised");
        }

        // Get the discovery and obtain JWK information

        final JsonObject discovery;
        try {
            discovery = FwUtils.getDiscoveryDocument(issuedProvider.getProviderDiscovery());
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Error while reading discovery details");
        }

        final String jwks_uri = discovery.getString("jwks_uri");

        if (jwks_uri == null) {
            return createErrorResponse("JWK details are not published by token issuer");
        }

        final JWK issuerJWK;

        try {
            issuerJWK = getJWKInformation(jwks_uri);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "JWK obtaining failed");
        }

        final boolean verified;
        try {
            verified = signedJWT.verify(new RSASSAVerifier((RSAPublicKey) issuerJWK.getParsedX509CertChain().get(0).getPublicKey()));
        } catch (JOSEException e) {
            return createErrorResponse(e, "JWK parsing failed");
        }

        if (!verified) {
            return createErrorResponse("Token signature validation error");
        }

        // Store SIP claims about end user
        final String sub = (String) jwtClaimsSet.getClaim("sub");

        final JSONObject sdata = (JSONObject) jwtClaimsSet.getClaim("sdata");
        final String email = sdata.getAsString("email");
        final Long age = (Long) sdata.getAsNumber("age");

        // Storing for admin purpose
        final SipUser sipUser = new SipUser(sub, (String) iss);
        sipUser.setAge(age.intValue());
        sipUser.setEmail(email);

        SipUserStorage.addNewUser(sipUser);

        final HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        stringObjectHashMap.put("sub", sub);
        stringObjectHashMap.put("email", email);
        stringObjectHashMap.put("age", age);

        // Token verified . Create response
        final String accessToken = FwUtils.getRandomId(15);

        final TokenObjectForSIPGrant tokenObjectForSIPGrant = new TokenObjectForSIPGrant(accessToken);

        tokenObjectForSIPGrant.setSipClaims(stringObjectHashMap);

        TokenStorage.addByAccessToken(tokenObjectForSIPGrant.getAccessToken(), tokenObjectForSIPGrant);

        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("access_token", tokenObjectForSIPGrant.getAccessToken());
        objectBuilder.add("token_type", "Bearer");

        return objectBuilder.build();
    }

    private static JsonObject createErrorResponse(final Throwable ex, final String message) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Error", message);

        if (ex instanceof FrameworkCheckedException) {
            objectBuilder.add("Cause", ((FrameworkCheckedException) ex).getCustomMessage());
        }

        objectBuilder.add("Details", ex.getMessage());
        return objectBuilder.build();
    }
}