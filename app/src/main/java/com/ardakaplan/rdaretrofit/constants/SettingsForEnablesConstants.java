package com.ardakaplan.rdaretrofit.constants;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ardakaplan on 05/11/15.
 * <p>
 * www.ardakaplan.com
 * <p>
 * run_count.kaplan09@gmail.com
 */
@SuppressWarnings("PointlessBooleanExpression")
public final class SettingsForEnablesConstants {

    //#IFDEF 'Debug'
    public static final HttpLoggingInterceptor.Level LOGGING_LEVEL=HttpLoggingInterceptor.Level.BODY;
    public static final boolean ENABLE_RDA_LOGGER = true;
    public static final boolean ENABLE_HTTP_LOGS = true;
    public static final boolean ENABLE_LIFE_CYCLE = false;
    //#IFDEF 'Release'
//    public static final HttpLoggingInterceptor.Level LOGGING_LEVEL=HttpLoggingInterceptor.Level.NONE;
    //public static final boolean ENABLE_RDA_LOGGER = false;
    //public static final boolean ENABLE_LIFE_CYCLE = false;
    //#ENDIF

    private SettingsForEnablesConstants() {

    }
}
