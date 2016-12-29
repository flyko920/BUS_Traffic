package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.bean.BastBean;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class NetApi {
    static ApiInterface mApiHelper = (ApiInterface) new ApiHelper<ApiInterface>().getInstance(ApiInterface.class);

    public static void getWeiXinPermission(Callback callback){
        Call<BastBean> mCall = mApiHelper.getWeiXinPermission();
        mCall.enqueue(callback);
    }

    public static void getUserActivateInfo(Callback callback,String imsi){
        Call<BastBean> mCall = mApiHelper.getUserActivateInfo(imsi);
        mCall.enqueue(callback);
    }

}
