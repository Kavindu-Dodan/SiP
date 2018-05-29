package Models;

import java.util.Map;

public class TokenObjectForSIPGrant extends TokenObject {

    private String accessToken;
    private Map<String, Object> sipClaims;

    public TokenObjectForSIPGrant(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Map<String, Object> getSipClaims() {
        return sipClaims;
    }

    public void setSipClaims(final Map<String, Object> sipClaims) {
        this.sipClaims = sipClaims;
    }
}
