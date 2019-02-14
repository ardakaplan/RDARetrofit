package com.ardakaplan.rdaretrofit.requests;

import android.support.annotation.Nullable;

import com.ardakaplan.rdaretrofit.requests.interfaces.GoogleServiceRetrofitInterfaces;
import com.ardakaplan.rdaretrofitlib.RDARequestListener;
import com.ardakaplan.rdaretrofitlib.RDARetrofitCallback;
import com.ardakaplan.rdaretrofitlib.requestException.RDARequestException;
import com.ardakaplan.rdaretrofitlib.retrofit.RDARetrofitProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Arda Kaplan on 31-Jan-19 - 13:25
 */
@Singleton
public class GoogleService extends BaseRequest {

    private RDARetrofitProvider rdaRetrofitProvider;

    @Inject
    public GoogleService(RDARetrofitProvider rdaRetrofitProvider) {
        this.rdaRetrofitProvider = rdaRetrofitProvider;
    }

    public void makeRequest(@Nullable String parameter, RDARequestListener<String> rdaRequestListener) {

        rdaRetrofitProvider.makeRequest(
                rdaRetrofitProvider.createRetrofit(GoogleServiceRetrofitInterfaces.class).makeRequest(), new RDARetrofitCallback<String>() {
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
