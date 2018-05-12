package Models;

import Common.FwUtils;

public abstract class TokenObject {

    private final String objId;
    private final String accessToken;
    private final String authCode;

    private final long createdEpoch;

    TokenObject(final String accessToken, final String authCode) {
        this.accessToken = accessToken;
        this.authCode = authCode;
        objId = FwUtils.getRandomId(12);
        createdEpoch = FwUtils.getCurrentTimeStamp();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public String getObjId() {
        return objId;
    }

    public long getCreatedEpoch() {
        return createdEpoch;
    }
}
