package com.ardakaplan.rdaretrofit.di.modules;

import com.ardakaplan.rdaretrofit.splash.SplashContract;
import com.ardakaplan.rdaretrofit.splash.SplashPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SplashModule {

    @Binds
    public abstract SplashContract.SplashPresenterContract providePresenter(SplashPresenter splashPresenter);
}
