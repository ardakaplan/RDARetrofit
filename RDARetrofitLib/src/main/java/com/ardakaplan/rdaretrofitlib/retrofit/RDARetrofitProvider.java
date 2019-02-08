package com.ardakaplan.rdaretrofitlib.retrofit;

import com.ardakaplan.rdaretrofitlib.RDARetrofitCallback;
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
public class RDARetrofitProvider {

    public static final class RetrofitManager {

        public static int TIME_OUT = 20;
        public static HttpLoggingInterceptor.Level LOGGING_LEVEL = HttpLoggingInterceptor.Level.NONE;
        public static String BASE_URL = "http://www.google.com/";
    }

    //public adjustable fields


    private Retrofit.Builder retrofitBuilder;
    private RDARetrofitErrorHandler RDARetrofitErrorHandler;

    @Inject
    RDARetrofitProvider(RDARetrofitErrorHandler RDARetrofitErrorHandler) {

        this.RDARetrofitErrorHandler = RDARetrofitErrorHandler;

        GsonBuilder gsonBuilder = new GsonBuilder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(RetrofitManager.LOGGING_LEVEL);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(RetrofitManager.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(RetrofitManager.TIME_OUT, TimeUnit.SECONDS)
                .build();

        retrofitBuilder = new Retrofit.Builder()
                .client(client)
                .baseUrl(RetrofitManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    }

    public <W> void makeRequest(Call<W> call, RDARetrofitCallback<W> rdaRetrofitCallback) {

        call.enqueue(new Callback<W>() {

            @Override
            public void onResponse(Call<W> call, Response<W> response) {

                RDARequestException rdaRequestException = RDARetrofitErrorHandler.checkError(response, null);

                if (rdaRequestException == null) {

                    rdaRetrofitCallback.onSuccess(response.body());

                } else {

                    rdaRetrofitCallback.onError(rdaRequestException);
                }
            }

            @Override
            public void onFailure(Call<W> call, Throwable t) {

                rdaRetrofitCallback.onError(RDARetrofitErrorHandler.checkError(null, t));
            }
        });
    }

    public <T> T createRetrofit(Class<T> service) {

        return retrofitBuilder.build().create(service);
    }
}
