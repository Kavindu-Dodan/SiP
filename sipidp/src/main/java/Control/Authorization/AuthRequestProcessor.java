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
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthRequestProcessor {

    private AuthRequestProcessor() {
    }

    public static boolean preValidations(final HttpServletRequest request) {
        try {
            AuthenticationRequest.parse(new URI(request.getRequestURI()), request.getQueryString());
        } catch (ParseException | URISyntaxException e) {
            throw new FrameworkBaseException("Failed to pass the Authorization request", e);`
        }
        return true;
    }
}
