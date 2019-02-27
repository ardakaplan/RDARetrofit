package com.ardakaplan.rdaretrofitlib;

import com.ardakaplan.rdalibrary.base.exceptions.HoustonWeHaveAProblemHere;
import com.ardakaplan.rdalibrary.base.exceptions.RDAInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction.RDAAuthorizationErrorInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction.RDAMethodNotAllowedInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction.RDANoNetworkInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction.RDANotFoundInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction.RDANullResponseInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction.RDAServerInteractionException;
import com.ardakaplan.rdaretrofitlib.exceptions.interaction.ServerMessageInteractionException;


/**
 * Created by Arda Kaplan on 27-Feb-19 - 16:44
 */
public class RequestErrorConverter {

    public static RDAInteractionException convertExceptions(RDARequestException rdaException) {


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
