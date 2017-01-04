package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.bean.DataVO;
import com.xcc.bustraffic.bustraffic.bean.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by flykozhang on 2016/12/16.
 */

public interface ApiInterface {

    @GET("/bus/services/api/weiXinPermission/getToken")
    Call<DataVO.UserInfoVO> getWeiXinPermission();


    @GET("/bus/services/api/user/getUserInfo")
    Call<DataVO.UserInfoVO> getUserActivateInfo(@Query("imsi") String imsi);


    @GET("/bus/services/api/appVersion/getAppVersion")
    Call<DataVO.VersionVO> getAppVersion(@Query("version") int version);

}
