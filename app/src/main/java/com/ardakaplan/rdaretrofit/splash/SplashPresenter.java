package com.ardakaplan.rdaretrofit.splash;

import com.ardakaplan.rdalibrary.base.ui.screen.RDAPresenter;
import com.ardakaplan.rdalogger.RDALogger;

import javax.inject.Inject;

public class SplashPresenter extends RDAPresenter<SplashContract.SplashViewContract> implements SplashContract.SplashPresenterContract {


    @Inject
    public SplashPresenter() {
    }

    @Override
    protected void onAttached() {

    }

    @Override
    protected void onDetached() {

    }

    @Override
    public void testPresenterContract() {

        RDALogger.info("SPLASH PRESENTER ÇALIŞTI");
    }
}
