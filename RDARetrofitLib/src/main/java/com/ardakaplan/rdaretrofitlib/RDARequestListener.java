package com.ardakaplan.rdaretrofitlib;

import com.ardakaplan.rdacommonmethodslib.http.RDARequestException;

/**
 * Created by Arda Kaplan on 31-Jan-19 - 13:28
 */
public interface RDARequestListener<W> {

    void onSuccess(W w);

    void onError(RDARequestException rdaRequestException);
}
