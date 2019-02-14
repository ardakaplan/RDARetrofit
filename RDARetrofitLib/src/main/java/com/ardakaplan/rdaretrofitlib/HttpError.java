package com.ardakaplan.rdaretrofitlib;

/**
 * Created by Arda Kaplan on 14-Feb-19 - 16:34
 */
public enum HttpError {

    AUTHENTICATION(401),
    NOT_FOUND(404),
    SERVER(500),
    UNKNOWN(-1),
    NULL_RESPONSE(-2),
    NO_NETWORK(-3),
    SERVER_MESSAGE(-4);

    private int httpErrorCode;

    HttpError(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }
}
