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

import storage.UserSessions;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        // Session must be created if it is not already created
        final HttpSession session = httpServletRequest.getSession(true);

        if (UserSessions.checkLoggedIn(session.getId())) {
            chain.doFilter(req, resp);
        } else {
            // Do pre-validations. Forward to login only if they are valid
            if (AuthRequestProcessor.preValidations(httpServletRequest)) {
                req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}