package com.ardakaplan.rdaretrofit;

import com.ardakaplan.rdalibrary.base.objects.RDAApplication;
import com.ardakaplan.rdalibrary.di.HasCustomActivityInjector;
import com.ardakaplan.rdalogger.RDALogger;
import com.ardakaplan.rdaretrofit.constants.SettingsForEnablesConstants;
import com.ardakaplan.rdaretrofit.di.AppComponent;
import com.ardakaplan.rdaretrofit.di.DaggerAppComponent;
import com.ardakaplan.rdaretrofitlib.RDARetrofitCallback;
import com.ardakaplan.rdaretrofitlib.retrofit.RDARetrofitProvider;

/**
 * Created by Arda Kaplan on 10.08.2018 - 17:44
 */
public class TestApplication extends RDAApplication implements HasCustomActivityInjector {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        RDALogger.start(getString(R.string.app_name)).enableLogging(true);


        initRDARetrofitLib();
    }

    private void initRDARetrofitLib() {
        RDARetrofitProvider.RetrofitManager.BASE_URL = "https://www.swansybeauty.com/";
        RDARetrofitProvider.RetrofitManager.TIME_OUT = 20;
        RDARetrofitProvider.RetrofitManager.LOGGING_LEVEL = SettingsForEnablesConstants.LOGGING_LEVEL;
    }


    @Override
    protected void initDagger() {

        appComponent = DaggerAppComponent.builder().application(this).build();

        appComponent.inject(this);
    }
}
