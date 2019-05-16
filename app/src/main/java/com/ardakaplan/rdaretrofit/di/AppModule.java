package com.ardakaplan.rdaretrofit.di;

import com.ardakaplan.rdalogger.RDALogger;
import com.ardakaplan.rdaretrofit.constants.SettingsForEnablesConstants;
import com.ardakaplan.rdaretrofitlib.retrofit.RetrofitSettings;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class AppModule {

    @Provides
    RetrofitSettings providesRetrofitSettings() {

        HttpLoggingInterceptor.Logger fileLogger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String s) {

                RDALogger.logHttpRequest("EEEE " + s);
            }
        };

        HttpLoggingInterceptor fileLoggerInterceptor = new HttpLoggingInterceptor(fileLogger);
        fileLoggerInterceptor.setLevel(SettingsForEnablesConstants.LOGGING_LEVEL);

        return new RetrofitSettings(20,
                "https://www.google.com/",
                SettingsForEnablesConstants.LOGGING_LEVEL, null, fileLoggerInterceptor);
    }

}
