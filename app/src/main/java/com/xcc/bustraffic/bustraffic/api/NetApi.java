package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.bean.BastBean;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class NetApi {
    static ApiInterface mApiHelper;

    private static void init(){
        if (mApiHelper==null){
            mApiHelper = (ApiInterface) new ApiHelper<ApiInterface>().getInstance(ApiInterface.class);
        }
    }

    public static void getFeed(Callback callback){
        init();
        Call<BastBean> mCall = mApiHelper.getFeed();
        mCall.enqueue(callback);
    }
}
