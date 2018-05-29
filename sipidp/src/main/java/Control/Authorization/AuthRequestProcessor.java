/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package Control.Authorization;

import Common.Exceptions.FrameworkCheckedException;
import Common.Exceptions.FrameworkUncheckedException;
import Common.FwUtils;
import Common.JWTCreator;
import Models.Client;
import Models.IdentityProvider;
import Models.OpenIDConnectObject;
import Models.SharedIdentityObject;
import Models.User;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import storage.Clients;
import storage.EndUsers;
import storage.IdentityProviders;
import storage.TokenStorage;
import storage.UserSessions;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Common.Constants.getThisIssuer;
import static Common.Constants.getIdentityShareScope;
import static java.lang.String.format;

public class AuthRequestProcessor {

    private AuthRequestProcessor() {
    }

    public static boolean preValidations(final HttpServletRequest request) {
        final AuthenticationRequest authRequest;
        try {
            authRequest = AuthenticationRequest.parse(new URI(request.getRequestURI()), request.getQueryString());
        } catch (ParseException | URISyntaxException e) {
            throw new FrameworkUncheckedException("Failed to pass the Authorization request", e);
        }

        final Client client;

        try {
            client = Clients.getClientOnId(authRequest.getClientID().getValue());
        } catch (FrameworkCheckedException e) {
            throw new FrameworkUncheckedException("Client not found", e);
        }

        if (!client.getRedirectUrl().equals(authRequest.getRedirectionURI().toString())) {
            throw new FrameworkUncheckedException("Invalid client identifier");
        }

        return true;
    }

    public static AuthenticationResponse getAuthResponse(final HttpServletRequest request) {
        final AuthenticationRequest authRequest;
        try {
            authRequest = AuthenticationRequest.parse(new URI(request.getRequestURI()), request.getQueryString());
        } catch (ParseException | URISyntaxException e) {
            throw new FrameworkUncheckedException("Failed to pass the Authorization request", e);
        }

        final Client client;

        try {
            client = Clients.getClientOnId(authRequest.getClientID().getValue());
        } catch (FrameworkCheckedException e) {
            throw new FrameworkUncheckedException("Client not found", e);
        }

        final User user;

        try {
            final String username = UserSessions.getLoggedInUser(request.getSession().getId());
            user = EndUsers.getUserByUsername(username);
        } catch (FrameworkCheckedException e) {
            throw new FrameworkUncheckedException("Client not found", e);
        }

        final String iss = getThisIssuer();

        // This is needed for different flows
        final ResponseType responseType = authRequest.getResponseType();

        if (responseType.impliesCodeFlow()) {
            return processForAuthorizationCodeFlow(authRequest, client, user, iss);
        } else {
            throw new FrameworkUncheckedException("Unsupported response type");
        }

    }

    private static AuthenticationResponse processForAuthorizationCodeFlow(
            final AuthenticationRequest authRequest,
            final Client client,
            final User user,
            final String issuer) {

        // This is common for any scenario
        final String authCode = FwUtils.getRandomId(15);
        final String accessToken = FwUtils.getRandomId(15);

        // This is needed for SIP
        final List<String> scopeValues = authRequest.getScope().toStringList();

        final OpenIDConnectObject openIDConnectObject;

        if (scopeValues.contains(getIdentityShareScope())) {
            openIDConnectObject =
                    new SharedIdentityObject(
                            authCode,
                            accessToken,
                            getIdTokenForRequest(issuer, user, client).serialize(),
                            getSIPTokenForRequest(issuer, user, client).serialize()
                    );
        } else {
            openIDConnectObject =
                    new OpenIDConnectObject(
                            authCode,
                            accessToken,
                            getIdTokenForRequest(issuer, user, client).serialize()
                    );
        }

        TokenStorage.addByAuthCode(authCode, openIDConnectObject);

        return new AuthenticationSuccessResponse(
                authRequest.getRedirectionURI(),
                new AuthorizationCode(authCode),
                null,
                null,
                authRequest.getState(),
                null,
                authRequest.getResponseMode());
    }

    private static SignedJWT getIdTokenForRequest(
            final String issuer,
            final User loggedInUser,
            final Client client) {
        final Map<String, Object> idTokenClaims = new HashMap<>();

        idTokenClaims.put("aud", client.getClientId());
        idTokenClaims.put("sub", loggedInUser.getUsername());
        idTokenClaims.put("iss", issuer);
        idTokenClaims.put("email", loggedInUser.getEmail());
        idTokenClaims.put("age", loggedInUser.getAge());

        // Time related claims
        final long currentTimeStamp = FwUtils.getCurrentTimeStamp();
        idTokenClaims.put("iat", currentTimeStamp);
        idTokenClaims.put("exp", FwUtils.getTokenExpiringTimeStamp(currentTimeStamp));

        final SignedJWT idToken;
        try {
            idToken = JWTCreator.createJWT(idTokenClaims);
        } catch (FrameworkCheckedException e) {
            throw new FrameworkUncheckedException("Something went wrong", e);
        }

        return idToken;
    }

    private static SignedJWT getSIPTokenForRequest(
            final String issuer,
            final User loggedInUser,
            final Client client) {

        final Map<String, Object> sipTOken = new HashMap<>();

        sipTOken.put("aud", client.getClientId());
        sipTOken.put("sub", loggedInUser.getUsername());
        sipTOken.put("iss", issuer);

        // Subject related information
        final Map<String, Object> subClaims = new HashMap<>();
        subClaims.put("email", loggedInUser.getEmail());
        subClaims.put("age", loggedInUser.getAge());

        sipTOken.put("sdata", subClaims);

        // Time related claims
        final long currentTimeStamp = FwUtils.getCurrentTimeStamp();
        sipTOken.put("iat", currentTimeStamp);
        sipTOken.put("exp", FwUtils.getTokenExpiringTimeStamp(currentTimeStamp));

        final Collection<IdentityProvider> providerList = IdentityProviders.getProviderList();

        final List<String> registeredProviders = new ArrayList<>();

        providerList.forEach(
                provider -> registeredProviders.add(provider.getProviderUrl())

        );

        sipTOken.put("ist", registeredProviders.toArray());

        final SignedJWT sipToken;
        try {
            sipToken = JWTCreator.createJWT(sipTOken);
        } catch (FrameworkCheckedException e) {
            throw new FrameworkUncheckedException("Something went wrong", e);
        }

        return sipToken;
    }

}
