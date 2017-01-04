package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.bean.DataVO;
import com.xcc.bustraffic.bustraffic.bean.Result;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class NetApi {
    static ApiInterface mApiHelper = (ApiInterface) new ApiHelper<ApiInterface>().getInstance(ApiInterface.class);

    public static void getWeiXinPermission(Callback callback){
        Call<DataVO.UserInfoVO> mCall = mApiHelper.getWeiXinPermission();
        mCall.enqueue(callback);
    }

    public static void getUserActivateInfo(Callback callback,String imsi){
        Call<DataVO.UserInfoVO> mCall = mApiHelper.getUserActivateInfo(imsi);
        mCall.enqueue(callback);
    }

    public static void getAppVersion(Callback callback,int version){
        Call<DataVO.VersionVO> mCall = mApiHelper.getAppVersion(version);
        mCall.enqueue(callback);
    }

}
