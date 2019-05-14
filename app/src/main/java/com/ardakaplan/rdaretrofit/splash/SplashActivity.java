package com.ardakaplan.rdaretrofit.splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.ardakaplan.rdalibrary.base.ui.screen.RDAActivity;
import com.ardakaplan.rdaretrofit.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends RDAActivity implements SplashContract.SplashViewContract {

    @BindView(R.id.button_test)
    Button testButton;

    @Inject
    SplashContract.SplashPresenterContract presenter;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState, R.layout.splash_activity);

        presenter.attach(this);

        testButton.setText("DENEME");
    }

    @OnClick(R.id.button_test)
    void test() {

        presenter.testPresenterContract();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detach();
    }
}
