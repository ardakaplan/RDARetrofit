package com.ardakaplan.rdaretrofit.requests;

import android.support.annotation.Nullable;

import com.ardakaplan.rdaretrofit.requests.interfaces.GoogleServiceRetrofitInterfaces;
import com.ardakaplan.rdaretrofitlib.RDARequestListener;
import com.ardakaplan.rdaretrofitlib.RDARetrofitCallback;
import com.ardakaplan.rdaretrofitlib.requestException.RDARequestException;
import com.ardakaplan.rdaretrofitlib.retrofit.RetrofitProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Arda Kaplan on 31-Jan-19 - 13:25
 */
@Singleton
public class GoogleService extends BaseRequest {

    private RetrofitProvider retrofitProvider;

    @Inject
    public GoogleService(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    public void makeRequest(@Nullable String parameter, RDARequestListener<String> rdaRequestListener) {

        retrofitProvider.makeRequest(
                retrofitProvider.createRetrofit(GoogleServiceRetrofitInterfaces.class).makeRequest(), new RDARetrofitCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        //dönen cevap parse edilecekse burada yapılır, yoksa sonuç direk set edilir

                        rdaRequestListener.onSuccess(s);
                    }

                    @Override
                    public void onError(RDARequestException e) {

                        rdaRequestListener.onError(e);
                    }
                });
    }
}
