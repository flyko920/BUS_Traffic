package com.xcc.bustraffic.bustraffic.service;

import android.content.Context;
import android.content.Intent;

import com.xcc.bustraffic.library.utils.L;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;

/**
 * Created by flykozhang on 2016/12/29.
 */
public class UpdataUiRunnable implements Runnable {

    private final Context mContext;
    private boolean activated;
    private boolean isActivateSucceed;

    public UpdataUiRunnable(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        while (!isActivateSucceed) {
            try {
                Thread.sleep(7 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activated = SharedPrefsUtil.getValue(mContext, "activated", false);

            if (activated) {
                // 告知界面更新
                Intent intent = new Intent("com.xcc.bustraffic.bustraffic.ui.activity");
                mContext.sendBroadcast(intent);
                isActivateSucceed=true;
            }
        }
    }
}
