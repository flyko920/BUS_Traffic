package com.xcc.bustraffic.bustraffic.api;

import com.xcc.bustraffic.bustraffic.bean.BastBean;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.library.net.RetrofitUtils;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class ApiHelper<T> {
    RetrofitUtils mRetrofitUtils;

    public Object getInstance(Class<T> clazz){
        if (mRetrofitUtils==null){
            mRetrofitUtils=new RetrofitUtils();
        }
        return  mRetrofitUtils.create(ApiComfig.BAST_URL,clazz);
    }
}
