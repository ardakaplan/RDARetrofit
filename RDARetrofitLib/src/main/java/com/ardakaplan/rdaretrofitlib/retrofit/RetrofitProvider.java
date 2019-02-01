package com.ardakaplan.rdaretrofitlib.retrofit;

import com.ardakaplan.rdaretrofitlib.RDARequestListener;
import com.ardakaplan.rdaretrofitlib.requestException.RDARequestException;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Singleton
public class RetrofitProvider {

    private Retrofit.Builder retrofitBuilder;
    private RetrofitErrorHandler retrofitErrorHandler;

    @Inject
    public RetrofitProvider(RetrofitErrorHandler retrofitErrorHandler) {

        this.retrofitErrorHandler = retrofitErrorHandler;

        GsonBuilder gsonBuilder = new GsonBuilder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofitBuilder = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://www.google.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    }

    public <W> void createRequest(Call<W> call, RDARequestListener<W> requestListener) {


        call.enqueue(new Callback<W>() {

            @Override
            public void onResponse(Call<W> call, Response<W> response) {

                RDARequestException rdaRequestException = retrofitErrorHandler.checkError(response, null);

                if (rdaRequestException == null) {

                    requestListener.onSuccess(response.body());
                } else {

                    requestListener.onError(rdaRequestException);
                }
            }

            @Override
            public void onFailure(Call<W> call, Throwable t) {

                requestListener.onError(retrofitErrorHandler.checkError(null, t));
            }
        });
    }

    public <T> T createRetrofit(Class<T> service) {

        return retrofitBuilder.build().create(service);
    }
}
