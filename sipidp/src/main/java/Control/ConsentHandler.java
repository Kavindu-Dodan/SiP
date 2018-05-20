package Control;

import Common.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/Consent")
public class ConsentHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String consentGrant = req.getParameter(Constants.getConsentField());

        if (consentGrant != null && "Accept".equals(consentGrant)) {
            final String queryString = req.getQueryString();
            resp.sendRedirect(req.getContextPath() + "/authorization?" + queryString);
        }

    }
}
