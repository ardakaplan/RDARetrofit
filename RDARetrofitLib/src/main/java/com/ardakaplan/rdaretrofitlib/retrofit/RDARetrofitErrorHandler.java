package com.ardakaplan.rdaretrofitlib.retrofit;

import android.support.annotation.Nullable;
import android.util.Log;

import com.ardakaplan.rdalibrary.helpers.RDAApplicationHelpers;
import com.ardakaplan.rdaretrofitlib.HttpErrorType;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;

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
    RDARetrofitErrorHandler(RDAApplicationHelpers rdaApplicationHelpers) {


        Log.i("AAAA","AAAAAAA + "+ rdaApplicationHelpers.getAppVersionName());
    }

    <W> RDARequestException checkError(@Nullable Response<W> response, @Nullable Throwable throwable) {

        if (response != null) {

            if (!response.isSuccessful()) {

                switch (response.code()) {

                    case 401:

                        return new RDARequestException(HttpErrorType.AUTHENTICATION_ERROR);

                    case 404:

                        return new RDARequestException(HttpErrorType.NOT_FOUND_ERROR);

                    case 405:

                        return new RDARequestException(HttpErrorType.METHOD_NOT_ALLOWED_ERROR);

                    case 500:

                        return new RDARequestException(HttpErrorType.SERVER_ERROR);

                    default:

                        return new RDARequestException(HttpErrorType.UNKNOWN_ERROR);
                }

            } else {

                if (response.body() == null) {

                    return new RDARequestException(HttpErrorType.NULL_RESPONSE_ERROR);

                } else {

                    return null;
                }
            }

        } else {

            if (throwable instanceof UnknownHostException) {

                return new RDARequestException(HttpErrorType.NO_NETWORK_ERROR);

            } else {

                return new RDARequestException(HttpErrorType.UNKNOWN_ERROR);
            }
        }
    }
}
