package com.xcc.bustraffic.bustraffic.api;

import android.util.Log;

import com.xcc.bustraffic.bustraffic.bean.BastBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class BastBeanApi {
    static NetApi mApiHelper;


    private static void create(){
        if (mApiHelper==null){
            mApiHelper = (NetApi) new ApiHelper<NetApi>().getInstance(NetApi.class);
        }
    }

    public static void getFeed(Callback callback){
        create();
        Call<BastBean> mCall = mApiHelper.getFeed();
        mCall.enqueue(callback);
    }
}
