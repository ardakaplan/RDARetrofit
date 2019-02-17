package com.ardakaplan.rdaretrofitlib.retrofit;

import android.support.annotation.Nullable;

import com.ardakaplan.rdacommonmethodslib.http.RDARequestException;

import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:01
 */
@Singleton
public class RDARetrofitErrorHandler {


    @Inject
    RDARetrofitErrorHandler() {
    }

    <W> RDARequestException checkError(@Nullable Response<W> response, @Nullable Throwable throwable) {

        if (response != null) {

            if (!response.isSuccessful()) {

                switch (response.code()) {

                    case 401:

                        return new RDARequestException(RDARequestException.HttpErrorType.AUTHENTICATION_ERROR);

                    case 404:

                        return new RDARequestException(RDARequestException.HttpErrorType.NOT_FOUND_ERROR);

                    case 500:

                        return new RDARequestException(RDARequestException.HttpErrorType.SERVER_ERROR);

                    default:

                        return new RDARequestException(RDARequestException.HttpErrorType.UNKNOWN_ERROR);
                }

            } else {

                if (response.body() == null) {

                    return new RDARequestException(RDARequestException.HttpErrorType.NULL_RESPONSE_ERROR);

                } else {

                    return null;
                }
            }

        } else {

            if (throwable instanceof UnknownHostException) {

                return new RDARequestException(RDARequestException.HttpErrorType.NO_NETWORK_ERROR);

            } else {

                return new RDARequestException(RDARequestException.HttpErrorType.UNKNOWN_ERROR);
            }
        }
    }
}
