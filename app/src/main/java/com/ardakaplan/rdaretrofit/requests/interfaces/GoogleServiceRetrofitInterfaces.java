package com.ardakaplan.rdaretrofit.requests.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Arda Kaplan on 31-Jan-19 - 13:31
 */
public interface GoogleServiceRetrofitInterfaces {

    @GET()
    Call<String> makeRequest();

}
