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
public class RetrofitProvider {

    //public adjustable fields
    public static int TIME_OUT = 20;
    public static HttpLoggingInterceptor.Level LOGGING_LEVEL = HttpLoggingInterceptor.Level.NONE;
    public static String BASE_URL = "http://www.google.com/";

    private Retrofit.Builder retrofitBuilder;
    private RetrofitErrorHandler retrofitErrorHandler;

    @Inject
    RetrofitProvider(RetrofitErrorHandler retrofitErrorHandler) {

        this.retrofitErrorHandler = retrofitErrorHandler;

        GsonBuilder gsonBuilder = new GsonBuilder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(LOGGING_LEVEL);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

        retrofitBuilder = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    }

    public <W> void makeRequest(Call<W> call, RDARetrofitCallback<W> rdaRetrofitCallback) {

        call.enqueue(new Callback<W>() {

            @Override
            public void onResponse(Call<W> call, Response<W> response) {

                RDARequestException rdaRequestException = retrofitErrorHandler.checkError(response, null);

                if (rdaRequestException == null) {

                    rdaRetrofitCallback.onSuccess(response.body());

                } else {

                    rdaRetrofitCallback.onError(rdaRequestException);
                }
            }

            @Override
            public void onFailure(Call<W> call, Throwable t) {

                rdaRetrofitCallback.onError(retrofitErrorHandler.checkError(null, t));
            }
        });
    }

    public <T> T createRetrofit(Class<T> service) {

        return retrofitBuilder.build().create(service);
    }
}
