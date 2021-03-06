package com.ardakaplan.rdaretrofitlib;

import com.ardakaplan.rdaretrofitlib.exceptions.RDARequestException;

/**
 * Created by Arda Kaplan on 01-Feb-19 - 09:26
 */
public interface RDARetrofitCallback<W> {

    void onSuccess(W w);

    void onError(RDARequestException rdaRequestException);
}
