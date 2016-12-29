package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.bean.BastBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by flykozhang on 2016/12/16.
 */

public interface ApiInterface {

    @GET("/bus/services/api/weiXinPermission/getToken")
    Call<BastBean> getWeiXinPermission();


    @GET("/bus/services/api/user/getUserInfo")
    Call<BastBean> getUserActivateInfo(@Query("imsi") String imsi);


}
