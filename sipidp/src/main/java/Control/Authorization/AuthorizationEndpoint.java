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

import Common.Exceptions.FrameworkUncheckedException;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/authorization", name = "AuthorizationEndpoint")
public class AuthorizationEndpoint extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        if (!AuthRequestProcessor.preValidations(req)) {
            throw new FrameworkUncheckedException("Invalid authorization request");
        }
        // Create the Authorization response
        AuthenticationResponse authResponse = AuthRequestProcessor.getAuthResponse(req);

        if (authResponse instanceof AuthenticationSuccessResponse) {
            AuthenticationSuccessResponse successResponse = (AuthenticationSuccessResponse) authResponse;
            resp.sendRedirect(successResponse.toURI().toASCIIString());
        }
    }
}
