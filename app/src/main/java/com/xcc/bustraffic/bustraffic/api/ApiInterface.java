package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.bean.BastBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by flykozhang on 2016/12/16.
 */

public interface ApiInterface {

    @GET("/bus/services/api/weiXinPermission/getToken")
    Call<BastBean> getFeed();

}
