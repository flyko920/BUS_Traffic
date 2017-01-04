package com.xcc.bustraffic.bustraffic.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.xcc.bustraffic.bustraffic.api.NetApi;
import com.xcc.bustraffic.bustraffic.api.callback.BastCallBack;
import com.xcc.bustraffic.bustraffic.bean.BastBean;
import com.xcc.bustraffic.library.utils.L;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;
import com.xcc.bustraffic.library.utils.SimInfoUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flykozhang on 2016/12/29.
 */

public class ActivateStateRunable implements Runnable {

    private final Context mContext;
    private boolean isActivateSucceed;
    public final String TAG = this.getClass().getSimpleName();

    public ActivateStateRunable(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        L.i(TAG,"run...............");
        while (!isActivateSucceed) {
            L.i("2222","轮询开始了··············");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getSimActivateSatae();
        }
    }

    private void getSimActivateSatae() {
        L.i(TAG,"getSimActivateSatae...............");
        NetApi.getUserActivateInfo(new BastCallBack<BastBean>(null) {
            @Override
            public void onResponse(Call<BastBean> call, Response<BastBean> response) {
                super.onResponse(call, response);
                if ("true".equals(response.body().isSuccess() + "")) {
                    SharedPrefsUtil.putValue(mContext,"activated",isActivateSucceed);
                    isActivateSucceed = true;
                }
            }
        }, SimInfoUtils.getSimSerialNumber(mContext));
    }
}
