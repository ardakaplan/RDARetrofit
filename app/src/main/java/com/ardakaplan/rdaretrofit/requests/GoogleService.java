package com.ardakaplan.rdaretrofit.requests;

import android.support.annotation.Nullable;

import com.ardakaplan.rdaretrofit.requests.interfaces.GoogleServiceRetrofitInterfaces;
import com.ardakaplan.rdaretrofitlib.RDARequestListener;
import com.ardakaplan.rdaretrofitlib.requestException.RDARequestException;
import com.ardakaplan.rdaretrofitlib.retrofit.RetrofitErrorHandler;
import com.ardakaplan.rdaretrofitlib.retrofit.RetrofitProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arda Kaplan on 31-Jan-19 - 13:25
 */
@Singleton
public class GoogleService extends BaseRequest {

    private RetrofitProvider retrofitProvider;
    private RetrofitErrorHandler retrofitErrorHandler;

    @Inject
    public GoogleService(RetrofitProvider retrofitProvider, RetrofitErrorHandler retrofitErrorHandler) {
        this.retrofitProvider = retrofitProvider;
        this.retrofitErrorHandler = retrofitErrorHandler;
    }

    public void makeRequest(@Nullable String parameter, RDARequestListener<String> rdaRequestListener) {

        retrofitProvider.createRetrofit(GoogleServiceRetrofitInterfaces.class).makeRequest().enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                RDARequestException rdaRequestException = retrofitErrorHandler.checkError(response, null);

                if (rdaRequestException == null) {

                    rdaRequestListener.onSuccess(response.body());
                } else {

                    rdaRequestListener.onError(rdaRequestException);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                rdaRequestListener.onError(retrofitErrorHandler.checkError(null, t));
            }
        });
    }
}
