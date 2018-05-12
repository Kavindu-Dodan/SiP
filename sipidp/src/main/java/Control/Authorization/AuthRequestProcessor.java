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

import Common.Exceptions.FrameworkBaseException;
import Common.Exceptions.FrameworkCheckedException;
import Common.FwUtils;
import Models.Client;
import Models.OpenIDConnectObject;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import storage.IDPClients;
import storage.TokenStorage;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthRequestProcessor {

    private AuthRequestProcessor() {
    }

    public static boolean preValidations(final HttpServletRequest request) {
        final AuthenticationRequest authRequest;
        try {
            authRequest = AuthenticationRequest.parse(new URI(request.getRequestURI()), request.getQueryString());
        } catch (ParseException | URISyntaxException e) {
            throw new FrameworkBaseException("Failed to pass the Authorization request", e);
        }

        final Client client;

        try {
            client = IDPClients.getClientOnId(authRequest.getClientID().getValue());
        } catch (FrameworkCheckedException e) {
            throw new FrameworkBaseException("Client not found", e);
        }

        return client.getRedirectUrl().equals(authRequest.getRedirectionURI().toString());
    }

    public static AuthenticationResponse getAuthResponse(final HttpServletRequest request) {
        final AuthenticationRequest authRequest;
        try {
            authRequest = AuthenticationRequest.parse(new URI(request.getRequestURI()), request.getQueryString());
        } catch (ParseException | URISyntaxException e) {
            throw new FrameworkBaseException("Failed to pass the Authorization request", e);
        }

        final ResponseType responseType = authRequest.getResponseType();

        final String accessToken = FwUtils.getRandomId(10);
        final String authCode = FwUtils.getRandomId(5);
        final String idToken = FwUtils.getRandomId(10);

        final OpenIDConnectObject openIDConnectObject = new OpenIDConnectObject(authCode, accessToken, idToken);

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
}
