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
import com.xcc.bustraffic.bustraffic.api.callback.BastBeanCallBack;
import com.xcc.bustraffic.bustraffic.bean.BastBean;
import com.xcc.bustraffic.bustraffic.bean.Data;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.service.PollingActivateStateService;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateFailureFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateSucceedFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.BuyFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.SimActivateFragment;
import com.xcc.bustraffic.library.utils.L;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;
import com.xcc.bustraffic.library.utils.SimInfoUtils;
import com.xcc.bustraffic.library.utils.WebViewUtils;
import com.xcc.bustraffic.library.utils.ZXingUtils;

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

    private class ActivateStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            showFragment(mActivateSucceedFragment, R.id.root);
            L.i(TAG,"onReceive...............");
//            unbindService(mActivateStateServiceConnection);
        }
    }


    @Override
    public int getLayoutId() {
        L.i(TAG,"getLayoutId...............");
        MobclickAgent.openActivityDurationTrack(false); //禁止默认的页面统计方式，这样将不会再自动统计Activity
        return R.layout.activity_main;
    }

    private void showSimActivateFragment() {
        L.i(TAG,"showSimActivateFragment...............");
        if (SimInfoUtils.getSimSerialNumber(this) == null) {
            mActivateSucceedFragment.setSimState(ActivateSucceedFragment.SIM_ERROR);
            mFragmentTransaction.add(R.id.root, mActivateSucceedFragment, "mActivateSucceedFragment");
        } else {
            mFragmentTransaction.add(R.id.root, mSimActivateFragment, "mSimActivateFragment");
        }
    }

    @Override
    public void initListener() {
        L.i(TAG,"initListener...............");
        mActivateFailureFragment = new ActivateFailureFragment();
        mSimActivateFragment = new SimActivateFragment();
        mBuyFragment = new BuyFragment();
        mActivateSucceedFragment = new ActivateSucceedFragment().setSimState(ActivateSucceedFragment.ACTIVATE_SUCCEED);
        mActivateFailureFragment.setmActivateFailureListener(new ActivateFailureFragment.ActivateFailureListener() {
            @Override
            public void reStart() {
                L.i(TAG,"reStart...............");
                showFragment(mSimActivateFragment, R.id.root);
            }

            @Override
            public void close() {
                L.i(TAG,"close...............");
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
                L.i(TAG,"showActivateState...............");
                if ("false".equals(success)) {
                    MainActivity.this.showFragment(mActivateFailureFragment, R.id.root);
                } else {
                    mActivateSucceedFragment.setSimState(ActivateSucceedFragment.ACTIVATE_SUCCEED);
                    MainActivity.this.showFragment(mActivateSucceedFragment, R.id.root);
                }
            }

            @Override
            public void setQRCodeClick() {
                L.i(TAG,"setQRCodeClick...............");
                mSimActivateFragment.showDialog(MainActivity.this);
            }

            @Override
            public void setUpdataRechargeUi(TextView mButton, ImageView mImageView) {
                L.i(TAG,"setUpdataRechargeUi...............");
                mButton.setText(R.string.main_sim_activate_recharge_over);
                Data data = (Data) SharedPrefsUtil.getObjectValue(MainActivity.this, "user_info", null, Data.class);
                if (null != data) {
                    userPhone = data.getUserPhone();
                }
                mImageView.setImageBitmap(ZXingUtils.createQRImage(ApiComfig.URL_PAY +
                                "phone=" + ("0".equals(userPhone) || userPhone == null ? "" : userPhone) +  //梦思要求，当没有手机号码或者ismi号码时，给空
                                "imsi=" + SimInfoUtils.getSimLine1Number(MainActivity.this),
                        mImageView.getWidth(), mImageView.getHeight()));
                        L.i("2222",ApiComfig.URL_PAY +
                                "phone=" + ("0".equals(userPhone) || userPhone == null ? "" : userPhone) +
                                "imsi=" + SimInfoUtils.getSimLine1Number(MainActivity.this));

            }

            @Override
            public void showSucceedFragment() {
                L.i(TAG,"showSucceedFragment...............");
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
        L.i(TAG,"initData...............");
        initActivateState();
        inspectVersionCode();
        if (!activated) {
            Intent service = new Intent(this, PollingActivateStateService.class);
            mActivateStateServiceConnection = new ActivateStateServiceConnection();
            bindService(service, mActivateStateServiceConnection, Service.BIND_AUTO_CREATE);
            startService(service);
            showSimActivateFragment();
        } else {
//            mFragmentTransaction.add(R.id.root, mSimActivateFragment, "mSimActivateFragment");
            showWebView();
        }
    }

    private void inspectVersionCode() {
        L.i(TAG,"inspectVersionCode...............");
        PackageInfo mPackageInfo = null;
        try {
            mPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            L.e("获取包名失败········");
        }
        final int currentVersionCode = mPackageInfo.versionCode;
        NetApi.getAutoUpdataApk(new BastBeanCallBack(null) {
            @Override
            public void onResponse(Call<BastBean> call, Response<BastBean> response) {
                super.onResponse(call, response);
                L.i(TAG,"onResponse...............");
                if ("true".equals(response.body().isSuccess() + "")) {
                    String newVersionCode = response.body().getData().get(0).getPackageName();
                    int aaaaaaa = newVersionCode.compareTo(String.valueOf(currentVersionCode));
                    L.e("newVersionCode == " + newVersionCode + ",currentVersionCode == " + currentVersionCode + ",aaaaaaa == " + aaaaaaa);
                }
            }
        }, mPackageInfo.versionCode);
    }

    private void updataApk() {
        L.i(TAG,"updataApk...............");
    }

    private void showWebView() {
        L.i(TAG,"showWebView...............");
        root.addView(WebViewUtils.getWebViewInstance(this, ApiComfig.URL_TEST_HTTP));
    }

    private void initActivateState() {
        L.i(TAG,"initActivateState...............");
        activated = SharedPrefsUtil.getValue(MainActivity.this, "activated", false);
    }

    @Override
    public void processClick(View v) {

    }

    private class ActivateStateServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.i(TAG,"onServiceConnected...............");

            mActivateStateServiceBinder = (PollingActivateStateService.ActivateStateServiceBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.i(TAG,"onServiceDisconnected...............");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!activated) {
            unbindService(mActivateStateServiceConnection);
        }
        unregisterReceiver(mActivateStateReceiver);
    }
}
