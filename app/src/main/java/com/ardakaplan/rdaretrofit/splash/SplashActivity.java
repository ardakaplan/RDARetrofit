package com.ardakaplan.rdaretrofit.splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.ardakaplan.rdalibrary.base.ui.screen.RDAActivity;
import com.ardakaplan.rdalogger.RDALogger;
import com.ardakaplan.rdaretrofit.R;
import com.ardakaplan.rdaretrofit.requests.GoogleService;
import com.ardakaplan.rdaretrofitlib.RDARequestListener;
import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends RDAActivity implements SplashContract.SplashViewContract {


    @BindView(R.id.button_test)
    Button testButton;

    @Inject
    SplashContract.SplashPresenterContract presenter;

    @Inject
    GoogleService googleService;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState, R.layout.splash_activity);

        presenter.attach(this);

        presenter.testPresenterContract();

        testButton.setText("DENEME");
    }

    @OnClick(R.id.button_test)
    void test() {

        RDALogger.info("LAHAANA");

        googleService.makeRequest("", new RDARequestListener<String>() {
            @Override
            public void onSuccess(String s) {

                RDALogger.info("ssss +" + s);
            }

            @Override
            public void onError(RDARequestException e) {

                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detach();
    }
}
