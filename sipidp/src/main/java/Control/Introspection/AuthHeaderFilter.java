package Control.Introspection;

import Common.Exceptions.FrameworkCheckedException;
import Common.FwUtils;
import storage.Clients;
import storage.TokenStorage;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Common.FwUtils.getCredentialFromBearerHeader;
import static Common.FwUtils.getCredentialsFromBasicHeader;

public class AuthHeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // Check header for a valid bearer token
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");


        if (FwUtils.isBasicAuth(authorizationHeader)) {
            try {
                final String[] credentialsFromBasicHeader = getCredentialsFromBasicHeader(authorizationHeader);

                if (Clients.authenticate(credentialsFromBasicHeader[0], credentialsFromBasicHeader[1])) {
                    chain.doFilter(request, response);
                    return;
                }
            } catch (FrameworkCheckedException e) {
                httpServletResponse.setStatus(401);
            }

        } else if (FwUtils.isBearerAuth(authorizationHeader)) {
            try {
                final String bearerToken = getCredentialFromBearerHeader(authorizationHeader);

                if (TokenStorage.verifyAccessToken(bearerToken)) {
                    chain.doFilter(request, response);
                    return;
                }
            } catch (FrameworkCheckedException e) {
                httpServletResponse.setStatus(401);
            }
        }

        httpServletResponse.setStatus(401);
    }

    @Override
    public void destroy() {

    }
}
