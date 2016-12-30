package com.xcc.bustraffic.bustraffic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by flykozhang on 2016/12/29.
 */
public class PollingActivateStateService extends Service {

    private ActivateStateServiceBinder mActivateStateServiceBinder;
    private Thread mThread;
    private boolean activated;
    private Thread updataUiThread;


    public class ActivateStateServiceBinder extends Binder{

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mActivateStateServiceBinder = new ActivateStateServiceBinder();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mThread = new Thread(new ActivateStateRunable(this));
        mThread.start();
        // 告知界面更新
        updataUiThread = new Thread(new UpdataUiRunnable(this));
        updataUiThread.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mActivateStateServiceBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mThread = null;
        return super.onUnbind(intent);
    }
}
