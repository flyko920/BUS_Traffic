package com.xcc.bustraffic.bustraffic.ui.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.api.NetApi;
import com.xcc.bustraffic.bustraffic.api.callback.BastCallBack;
import com.xcc.bustraffic.bustraffic.bean.DataVO;
import com.xcc.bustraffic.bustraffic.bean.Result;
import com.xcc.bustraffic.bustraffic.bean.UserInfo;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.service.PollingActivateStateService;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateFailureFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateSucceedFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.BuyFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.SimActivateFragment;
import com.xcc.bustraffic.bustraffic.utils.ApkUpdateHelper;
import com.xcc.bustraffic.bustraffic.view.ForcedUpdatingDialog;
import com.xcc.bustraffic.library.utils.L;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;
import com.xcc.bustraffic.library.utils.SimInfoUtils;
import com.xcc.bustraffic.library.utils.WebViewUtils;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Bind(R.id.root)
    RelativeLayout root;
    private SimActivateFragment mSimActivateFragment;
    private ActivateSucceedFragment mActivateSucceedFragment;
    private boolean activated;
    private ActivateFailureFragment mActivateFailureFragment;
    private ActivateStateServiceConnection mActivateStateServiceConnection;
    private PollingActivateStateService.ActivateStateServiceBinder mActivateStateServiceBinder;
    private ActivateStateReceiver mActivateStateReceiver;
    private BuyFragment mBuyFragment;
    private String packageDay;

    private class ActivateStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            showFragment(mActivateSucceedFragment, R.id.root);
            L.i(TAG, "onReceive...............");
//            unbindService(mActivateStateServiceConnection);
        }
    }


    @Override
    public int getLayoutId() {
        L.i(TAG, "getLayoutId...............");
        MobclickAgent.openActivityDurationTrack(false);                         //禁止默认的页面统计方式，这样将不会再自动统计Activity
        return R.layout.activity_main;
    }

    private void showSimActivateFragment() {
        L.i(TAG, "showSimActivateFragment...............");
        if (SimInfoUtils.getSimSerialNumber(this) == null) {
            mActivateSucceedFragment.setSimState(ActivateSucceedFragment.SIM_ERROR);
            mFragmentTransaction.add(R.id.root, mActivateSucceedFragment, "mActivateSucceedFragment");
        } else {
//            mFragmentTransaction.add(R.id.root, mSimActivateFragment, "mSimActivateFragment");
            showFragment(mSimActivateFragment,R.id.root);
        }
    }

    @Override
    public void initListener() {
        L.i(TAG, "initListener...............");
        mActivateFailureFragment = new ActivateFailureFragment();
        mSimActivateFragment = new SimActivateFragment();
        mBuyFragment = new BuyFragment();
        mBuyFragment.setBuyFragmentListener(new BuyFragment.BuyFragmentListener() {
            @Override
            public void okOnClick() {
                initActivateState();
            }
        });
        mActivateSucceedFragment = new ActivateSucceedFragment().setSimState(ActivateSucceedFragment.ACTIVATE_SUCCEED);
        mActivateFailureFragment.setmActivateFailureListener(new ActivateFailureFragment.ActivateFailureListener() {
            @Override
            public void reStart() {
                L.i(TAG, "reStart...............");
                showFragment(mSimActivateFragment, R.id.root);
            }

            @Override
            public void close() {
                L.i(TAG, "close...............");
                onBackPressed();
            }
        });

        mActivateSucceedFragment.setActivateSucceedClickListener(new ActivateSucceedFragment.ActivateSucceedClickListener() {
            @Override
            public void setOnClick() {
                onBackPressed();
            }
        });
        mSimActivateFragment.setSimActivateClickListener(new SimActivateFragment.SimActivateClickListener() {

            private String userPhone;

            @Override
            public void showActivateState(String success) {
                L.i(TAG, "showActivateState...............");
                if ("false".equals(success)) {
                    MainActivity.this.showFragment(mActivateFailureFragment, R.id.root);
                } else {
                    mActivateSucceedFragment.setSimState(ActivateSucceedFragment.ACTIVATE_SUCCEED);
                    MainActivity.this.showFragment(mActivateSucceedFragment, R.id.root);
                }
            }

            @Override
            public void setUpdataRechargeUi(TextView mButton, ImageView mImageView) {
                L.i(TAG, "setUpdataRechargeUi...............");
                mBuyFragment.setTitle(packageDay);
                showFragment(mBuyFragment, R.id.root);
            }

            @Override
            public void showSucceedFragment() {
                L.i(TAG, "showSucceedFragment...............");
//                showFragment(mActivateSucceedFragment, R.id.root);
                onBackPressed();
            }
        });

        // 注册广播
        IntentFilter filter = new IntentFilter("com.xcc.bustraffic.bustraffic.ui.activity");
        mActivateStateReceiver = new ActivateStateReceiver();
        registerReceiver(mActivateStateReceiver, filter);
    }


    @Override
    public void initData() {
        L.i(TAG, "initData...............");
        initActivateState();  //获取激活状态
        inspectVersionCode();   //检查版本更新
    }

    //检查版本更新
    private void inspectVersionCode() {
        L.i(TAG, "inspectVersionCode...............");
        PackageInfo mPackageInfo = null;
        try {
            mPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            L.e("获取包名失败········");
        }
        NetApi.getAppVersion(new BastCallBack<DataVO.VersionVO>(null) {
            @Override
            public void onResponse(Call<DataVO.VersionVO> call, Response<DataVO.VersionVO> response) {
                super.onResponse(call, response);
                updateMode(response);
            }
        }, mPackageInfo.versionCode);
    }

    private void updateMode(Response<DataVO.VersionVO> response) {
        ApkUpdateHelper mApkUpdateHelper = new ApkUpdateHelper();
        Integer updateStatus = response.body().getData().get(0).getUpdateStatus();
        String url = response.body().getData().get(0).getDownloadUrl();
        switch (updateStatus) {
            case 1:                                                 //普通更新
                mApkUpdateHelper.download(this, url);
                break;
            case 2:                                                 //强制更新
                ForcedUpdatingDialog.showDialog(this);
                mApkUpdateHelper.download(this, url);
                break;
            case 3:                                                 //不更新

                break;
        }

    }


    private void updataApk() {
        L.i(TAG, "updataApk...............");
    }

    private void showWebView() {
        L.i(TAG, "showWebView...............");
        root.addView(WebViewUtils.getWebViewInstance(this, ApiComfig.URL_MEMBER));
    }

    //初始化激活状态
    private void initActivateState() {
        L.i(TAG, "initActivateState...............");
        NetApi.getUserActivateInfo(new BastCallBack<DataVO.UserInfoVO>(null) {
            @Override
            public void onResponse(Call<DataVO.UserInfoVO> call, Response<DataVO.UserInfoVO> response) {
                super.onResponse(call, response);
                showSuitablePage(response);
            }
        }, SimInfoUtils.getSimSerialNumber(this));
    }

    /*根据用户信息，显示对应的页面*/
    private void showSuitablePage(Response<DataVO.UserInfoVO> response) {
        L.i("22222", "22222222222222222222" + response.body().toString());
        if (null!= response.body().getData()) {
            packageDay = response.body().getData().get(0).getPackageDay();
        }
        if ("true".equals(response.body().isSuccess() + "")) {                                                            // SIM卡已经激活成功
            activated = SharedPrefsUtil.getValue(MainActivity.this, "activated", false);
            SharedPrefsUtil.putObjectValue(MainActivity.this, "user_info", response.body().getData().get(0));
            if (0 > ApiComfig.PACKAGE_DAY.compareTo(packageDay)) {                                                          //小于设定值，显示提醒用户当前剩余天数
                mSimActivateFragment.showDialog(MainActivity.this, packageDay);
            } else if ("0".equals(packageDay)) {                                                                          // 小于0天，服务到期，提示充值页面
                showFragment(mBuyFragment, R.id.root);                                                                   //提示充值页面
            } else {
                showWebView();                                                                                           // 显示会员H5页面
            }
        } else {                                                                                                         // SIM卡未激活,显示SIM卡激活页面，并开启轮询服务
            Intent service = new Intent(MainActivity.this, PollingActivateStateService.class);
            mActivateStateServiceConnection = new ActivateStateServiceConnection();
            bindService(service, mActivateStateServiceConnection, Service.BIND_AUTO_CREATE);
            startService(service);                                                                                          //开启轮询服务
            showSimActivateFragment();                                                                                      //显示SIM卡激活页面
        }
    }

    @Override
    public void processClick(View v) {

    }

    private class ActivateStateServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.i(TAG, "onServiceConnected...............");

            mActivateStateServiceBinder = (PollingActivateStateService.ActivateStateServiceBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.i(TAG, "onServiceDisconnected...............");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!activated && mActivateStateServiceConnection != null) {
            unbindService(mActivateStateServiceConnection);
        }
        unregisterReceiver(mActivateStateReceiver);
    }
}
