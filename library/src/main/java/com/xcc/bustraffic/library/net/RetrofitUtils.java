package com.xcc.bustraffic.library.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class RetrofitUtils<T> {
    Retrofit retrofit;

    public Object create(String bastUrl, Class<T> clazz) {
        if (retrofit==null){
            retrofit = new Retrofit.Builder().
                    baseUrl(bastUrl).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit.create(clazz);
    }
}
