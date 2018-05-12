package Models;

import Common.FwUtils;

public abstract class TokenObject {

    private final String objId;
    private final long createdEpoch;

    TokenObject() {
        objId = FwUtils.getRandomId(12);
        createdEpoch = FwUtils.getCurrentTimeStamp();
    }

    public String getObjId() {
        return objId;
    }

    public long getCreatedEpoch() {
        return createdEpoch;
    }
}
