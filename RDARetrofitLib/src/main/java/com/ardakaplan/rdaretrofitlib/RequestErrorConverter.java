package com.ardakaplan.rdaretrofitlib;

import com.ardakaplan.rdalibrary.base.interactions.exceptions.HoustonWeHaveAProblemHere;
import com.ardakaplan.rdalibrary.base.interactions.exceptions.RDAInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction_exceptions.RDAAuthorizationErrorInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction_exceptions.RDAMethodNotAllowedInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction_exceptions.RDANoNetworkInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction_exceptions.RDANotFoundInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction_exceptions.RDANullResponseInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction_exceptions.RDAServerInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction_exceptions.ServerMessageInteractionException;


/**
 * Created by Arda Kaplan on 27-Feb-19 - 16:44
 */
public class RequestErrorConverter {

    protected RDAInteractionException convertExceptions(RDARequestException rdaException) {


        if (rdaException.getCode() == HttpErrorType.AUTHENTICATION_ERROR.getErrorCode()) {

            return new RDAAuthorizationErrorInteractionException();

        } else if (rdaException.getCode() == HttpErrorType.SERVER_ERROR.getErrorCode()) {

            return new RDAServerInteractionException();

        } else if (rdaException.getCode() == HttpErrorType.NOT_FOUND_ERROR.getErrorCode()) {

            return new RDANotFoundInteractionException();

        } else if (rdaException.getCode() == HttpErrorType.METHOD_NOT_ALLOWED_ERROR.getErrorCode()) {

            return new RDAMethodNotAllowedInteractionException();

        } else if (rdaException.getCode() == HttpErrorType.NO_NETWORK_ERROR.getErrorCode()) {

            return new RDANoNetworkInteractionException();

        } else if (rdaException.getCode() == HttpErrorType.NULL_RESPONSE_ERROR.getErrorCode()) {

            return new RDANullResponseInteractionException();

        } else if (rdaException.getCode() == HttpErrorType.SHOW_SERVER_MESSAGE_ERROR.getErrorCode()) {

            return new ServerMessageInteractionException(rdaException.getMessage());

        } else {

            return new HoustonWeHaveAProblemHere();
        }
    }
}
