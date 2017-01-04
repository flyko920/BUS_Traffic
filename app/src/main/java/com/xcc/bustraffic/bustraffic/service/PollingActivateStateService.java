package com.xcc.bustraffic.bustraffic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xcc.bustraffic.library.utils.L;

/**
 * Created by flykozhang on 2016/12/29.
 */
public class PollingActivateStateService extends Service {

    private ActivateStateServiceBinder mActivateStateServiceBinder;
    private Thread mThread;
    private boolean activated;
    private Thread updataUiThread;
    public final String TAG = this.getClass().getSimpleName();


    public class ActivateStateServiceBinder extends Binder{

    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.i(TAG,"onCreate...............");
        mActivateStateServiceBinder = new ActivateStateServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i(TAG,"onStartCommand...............");
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
        L.i(TAG,"onBind...............");
        return mActivateStateServiceBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.i(TAG,"onUnbind...............");
        mThread = null;
        return super.onUnbind(intent);
    }
}
