package com.ardakaplan.rdaretrofit.di;

import com.ardakaplan.rdaretrofit.di.modules.SplashModule;
import com.ardakaplan.rdaretrofit.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {SplashModule.class})
    abstract SplashActivity bindSplashActivity();

}
