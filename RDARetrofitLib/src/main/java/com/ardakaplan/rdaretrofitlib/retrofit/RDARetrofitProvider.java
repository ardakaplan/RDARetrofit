package com.ardakaplan.rdaretrofitlib.retrofit;

import com.ardakaplan.rdalogger.RDALogger;
import com.ardakaplan.rdaretrofitlib.RDARetrofitCallback;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
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
        public static List<RegisterTypeAdapter> registerTypeAdapters;
    }

    public static class RegisterTypeAdapter {

        private Type type;
        private Object typeAdapter;

        public RegisterTypeAdapter(Type type, Object typeAdapter) {
            this.type = type;
            this.typeAdapter = typeAdapter;
        }
    }

    //public adjustable fields


    private Retrofit.Builder retrofitBuilder;
    private RDARetrofitErrorHandler rdaRetrofitErrorHandler;

    @Inject
    RDARetrofitProvider(RDARetrofitErrorHandler rdaRetrofitErrorHandler) {

        this.rdaRetrofitErrorHandler = rdaRetrofitErrorHandler;

        GsonBuilder gsonBuilder = new GsonBuilder();

        if (RetrofitManager.registerTypeAdapters != null && RetrofitManager.registerTypeAdapters.size() > 0) {

            for (RegisterTypeAdapter registerTypeAdapter : RetrofitManager.registerTypeAdapters) {

                gsonBuilder.registerTypeAdapter(registerTypeAdapter.type, registerTypeAdapter.typeAdapter);
            }
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        {
            interceptor.setLevel(RetrofitManager.LOGGING_LEVEL);
        }

        HttpLoggingInterceptor.Logger fileLogger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String s) {

                RDALogger.logHttpRequest(s);
            }
        };

        HttpLoggingInterceptor fileLoggerInterceptor = new HttpLoggingInterceptor(fileLogger);
        fileLoggerInterceptor.setLevel(RetrofitManager.LOGGING_LEVEL);


        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
                .addInterceptor(fileLoggerInterceptor)
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
