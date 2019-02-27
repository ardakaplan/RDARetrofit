package com.ardakaplan.rdaretrofitlib.exceptions;

import com.ardakaplan.rdalibrary.base.exceptions.RDAException;
import com.ardakaplan.rdaretrofitlib.HttpErrorType;

/**
 * Created by Arda Kaplan on 27-Feb-19 - 16:57
 */
public class RDARequestException extends RDAException {


    public RDARequestException(HttpErrorType httpErrorType) {
        super(httpErrorType.getErrorCode(), httpErrorType.getMessage());
    }

    public RDARequestException(int code, String message) {
        super(code, message);
    }
}
