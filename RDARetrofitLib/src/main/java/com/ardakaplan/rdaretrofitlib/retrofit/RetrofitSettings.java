package com.ardakaplan.rdaretrofitlib.retrofit;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Arda Kaplan on 14-May-19 - 14:02
 */
public class RetrofitSettings {

    private final int TIME_OUT;
    private final String BASE_URL;
    private final HttpLoggingInterceptor.Level LOGGING_LEVEL;
    private final List<RDARetrofitProvider.RegisterTypeAdapter> REGISTER_TYPE_ADAPTERS;

    //if you need http logs
    private final HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR;

    public RetrofitSettings(int TIME_OUT, String BASE_URL, HttpLoggingInterceptor.Level LOGGING_LEVEL, List<RDARetrofitProvider.RegisterTypeAdapter> REGISTER_TYPE_ADAPTERS, HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR) {
        this.TIME_OUT = TIME_OUT;
        this.BASE_URL = BASE_URL;
        this.LOGGING_LEVEL = LOGGING_LEVEL;
        this.REGISTER_TYPE_ADAPTERS = REGISTER_TYPE_ADAPTERS;
        this.HTTP_LOGGING_INTERCEPTOR = HTTP_LOGGING_INTERCEPTOR;
    }

    public int getTIME_OUT() {
        return TIME_OUT;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public HttpLoggingInterceptor.Level getLOGGING_LEVEL() {
        return LOGGING_LEVEL;
    }

    public List<RDARetrofitProvider.RegisterTypeAdapter> getREGISTER_TYPE_ADAPTERS() {
        return REGISTER_TYPE_ADAPTERS;
    }

    public HttpLoggingInterceptor getHTTP_LOGGING_INTERCEPTOR() {
        return HTTP_LOGGING_INTERCEPTOR;
    }
}
