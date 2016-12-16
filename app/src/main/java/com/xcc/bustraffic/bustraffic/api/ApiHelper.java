package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class ApiHelper<T> {
    Retrofit retrofit;

    public Object getInstance(Class<T> clazz){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().
                baseUrl(ApiComfig.BAST_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        }
        return retrofit.create(clazz);
    }
}
