package com.ardakaplan.rdaretrofitlib;

/**
 * Created by Arda Kaplan on 27-Feb-19 - 16:32
 */
public enum HttpErrorType {

    AUTHENTICATION_ERROR(401, "error Code : 401 || AUTHENTICATION_ERROR"),
    NOT_FOUND_ERROR(404, "error Code : 404 || NOT_FOUND_ERROR"),
    METHOD_NOT_ALLOWED_ERROR(405, "error Code : 405 || METHOD_NOT_ALLOWED_ERROR"),
    SERVER_ERROR(500, "error Code : 500 || SERVER_ERROR"),
    UNKNOWN_ERROR(-1, "error Code : -1 || UNKNOWN_ERROR"),
    NULL_RESPONSE_ERROR(-2, "error Code : -2 || NULL_RESPONSE_ERROR"),
    NO_NETWORK_ERROR(-3, "error Code : -3 || NO_NETWORK_ERROR"),
    SHOW_SERVER_MESSAGE_ERROR(-4, "error Code : -4 || SHOW_SERVER_MESSAGE_ERROR");

    private int errorCode;
    private String message;

    HttpErrorType(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
