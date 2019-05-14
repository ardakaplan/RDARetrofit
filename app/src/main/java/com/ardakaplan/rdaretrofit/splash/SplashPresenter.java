package com.ardakaplan.rdaretrofit.splash;

import com.ardakaplan.rdalibrary.base.ui.screen.RDAPresenter;
import com.ardakaplan.rdalogger.RDALogger;
import com.ardakaplan.rdaretrofit.requests.GoogleService;
import com.ardakaplan.rdaretrofitlib.RDARequestListener;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;

import javax.inject.Inject;

public class SplashPresenter extends RDAPresenter<SplashContract.SplashViewContract> implements SplashContract.SplashPresenterContract {


    private GoogleService googleService;

    @Inject
    public SplashPresenter(GoogleService googleService) {

        this.googleService = googleService;
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


        googleService.makeRequest("", new RDARequestListener<String>() {
            @Override
            public void onSuccess(String s) {

                RDALogger.info("RESPONSE " + s);
            }

            @Override
            public void onError(RDARequestException e) {

                e.printStackTrace();
            }
        });
    }
}
