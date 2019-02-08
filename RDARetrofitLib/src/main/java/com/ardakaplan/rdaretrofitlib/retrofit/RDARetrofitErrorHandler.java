package com.ardakaplan.rdaretrofitlib.retrofit;

import android.support.annotation.Nullable;

import com.ardakaplan.rdaretrofitlib.requestException.NoNetworkRDARequestException;
import com.ardakaplan.rdaretrofitlib.requestException.NotFoundRDARequestException;
import com.ardakaplan.rdaretrofitlib.requestException.NullResponseRDARequestException;
import com.ardakaplan.rdaretrofitlib.requestException.RDARequestException;
import com.ardakaplan.rdaretrofitlib.requestException.ServerRDARequestException;
import com.ardakaplan.rdaretrofitlib.requestException.UnknownRDARequestException;

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

                    case 404:

                        return new NotFoundRDARequestException(throwable, response);

                    case 500:

                        return new ServerRDARequestException(throwable, response);

                    default:

                        return new UnknownRDARequestException(throwable, response);
                }

            } else {

                if (response.body() == null) {

                    return new NullResponseRDARequestException(throwable, response);

                } else {

                    return null;
                }
            }

        } else {

            if (throwable instanceof UnknownHostException) {

                return new NoNetworkRDARequestException(throwable, response);

            } else {

                return null;
            }
        }
    }
}
