package com.ardakaplan.rdaretrofit.splash;

import com.ardakaplan.rdalibrary.base.ui.screen.presenters.RDAPresenterContract;
import com.ardakaplan.rdalibrary.base.ui.screen.views.RDAViewContract;

public class SplashContract {

    public interface SplashViewContract extends RDAViewContract {

    }

    public interface SplashPresenterContract extends RDAPresenterContract<SplashViewContract> {

        void testPresenterContract();
    }
}
