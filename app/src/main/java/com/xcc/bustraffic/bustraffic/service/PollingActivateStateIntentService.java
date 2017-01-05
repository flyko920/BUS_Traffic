package com.xcc.bustraffic.bustraffic.service;

import android.app.IntentService;
import android.content.Intent;

import com.xcc.bustraffic.bustraffic.api.NetApi;
import com.xcc.bustraffic.bustraffic.api.callback.BastCallBack;
import com.xcc.bustraffic.bustraffic.bean.DataVO;
import com.xcc.bustraffic.library.utils.L;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;
import com.xcc.bustraffic.library.utils.SimInfoUtils;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by flykozhang on 2017/1/5.
 */

public class PollingActivateStateIntentService extends IntentService {
    public final String TAG = this.getClass().getSimpleName();
//    public PollingActivateStateIntentService(String name) {
//        super(name);
//    }

    public PollingActivateStateIntentService() {
        super("PollingActivateStateIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        L.i(TAG, "onHandleIntent...............");
        do {
            getUserInfoSuccessOf();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (SharedPrefsUtil.getValue(PollingActivateStateIntentService.this, "activated", false)) {
                notificationUiUpdata();
            }
        }
        while (!SharedPrefsUtil.getValue(PollingActivateStateIntentService.this, "activated", false));
    }

    // 告知界面更新
    private void notificationUiUpdata() {
        L.i(TAG, "notificationUiUpdata...............");
        Intent intent = new Intent("com.xcc.bustraffic.bustraffic.ui.activity");
        PollingActivateStateIntentService.this.sendBroadcast(intent);
    }

    private void getUserInfoSuccessOf() {
        L.i(TAG, "getUserInfoSuccessOf...............");
        NetApi.getUserActivateInfo(new BastCallBack<DataVO.UserInfoVO>(null) {
            @Override
            public void onResponse(Call<DataVO.UserInfoVO> call, Response<DataVO.UserInfoVO> response) {
                super.onResponse(call, response);
                if ("true".equals(response.body().isSuccess() + "")) {
                    SharedPrefsUtil.putValue(PollingActivateStateIntentService.this, "activated", true);
                }
            }
        }, SimInfoUtils.getSimSerialNumber(PollingActivateStateIntentService.this));
    }
}
