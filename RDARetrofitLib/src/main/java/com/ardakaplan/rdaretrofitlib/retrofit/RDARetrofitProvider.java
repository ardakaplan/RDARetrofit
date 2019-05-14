package com.ardakaplan.rdaretrofitlib.retrofit;

import com.ardakaplan.rdaretrofitlib.RDARetrofitCallback;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
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

    public static class RegisterTypeAdapter {

        private Type type;
        private Object typeAdapter;

        public RegisterTypeAdapter(Type type, Object typeAdapter) {
            this.type = type;
            this.typeAdapter = typeAdapter;
        }
    }

    private Retrofit.Builder retrofitBuilder;
    private RDARetrofitErrorHandler rdaRetrofitErrorHandler;

    @Inject
    RDARetrofitProvider(RDARetrofitErrorHandler rdaRetrofitErrorHandler, RetrofitSettings retrofitSettings) {

        this.rdaRetrofitErrorHandler = rdaRetrofitErrorHandler;

        GsonBuilder gsonBuilder = new GsonBuilder();

        if (retrofitSettings.getREGISTER_TYPE_ADAPTERS() != null && retrofitSettings.getREGISTER_TYPE_ADAPTERS().size() > 0) {

            for (RegisterTypeAdapter registerTypeAdapter : retrofitSettings.getREGISTER_TYPE_ADAPTERS()) {

                gsonBuilder.registerTypeAdapter(registerTypeAdapter.type, registerTypeAdapter.typeAdapter);
            }
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        {
            interceptor.setLevel(retrofitSettings.getLOGGING_LEVEL());
        }


        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(interceptor);

        //TODO harici loglama
//        if (retrofitSettings.getHTTP_LOGGING_INTERCEPTOR() != null) {
//
//            builder.addInterceptor(retrofitSettings.httpLoggingForApp);
//        }

        OkHttpClient okHttpClient = builder.readTimeout(retrofitSettings.getTIME_OUT(), TimeUnit.SECONDS)
                .connectTimeout(retrofitSettings.getTIME_OUT(), TimeUnit.SECONDS)
                .build();

        retrofitBuilder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(retrofitSettings.getBASE_URL())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    }

    public <W> void makeRequest(Call<W> call, RDARetrofitCallback<W> rdaRetrofitCallback) {

        call.enqueue(new Callback<W>() {

            @Override
            public void onResponse(Call<W> call, Response<W> response) {

                RDARequestException rdaRequestException = rdaRetrofitErrorHandler.checkError(response, null);

                if (rdaRequestException == null) {

                    rdaRetrofitCallback.onSuccess(response.body());

                } else {

                    rdaRetrofitCallback.onError(rdaRequestException);
                }
            }

            @Override
            public void onFailure(Call<W> call, Throwable t) {

                rdaRetrofitCallback.onError(rdaRetrofitErrorHandler.checkError(null, t));
            }
        });
    }

    public <T> T createRetrofit(Class<T> service) {

        return retrofitBuilder.build().create(service);
    }
}
