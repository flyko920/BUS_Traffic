package com.xcc.bustraffic.bustraffic.ui.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.bean.Data;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.service.PollingActivateStateService;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateFailureFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateSucceedFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.BuyFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.SimActivateFragment;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;
import com.xcc.bustraffic.library.utils.SimInfoUtils;
import com.xcc.bustraffic.library.utils.WebViewUtils;
import com.xcc.bustraffic.library.utils.ZXingUtils;

import butterknife.Bind;

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
//            unbindService(mActivateStateServiceConnection);
        }
    }


    @Override
    public int getLayoutId() {
        MobclickAgent.openActivityDurationTrack(false); //禁止默认的页面统计方式，这样将不会再自动统计Activity
        return R.layout.activity_main;
    }

    private void showSimActivateFragment() {
        if (SimInfoUtils.getSimSerialNumber(this) == null) {
            mActivateSucceedFragment.setSimState(ActivateSucceedFragment.SIM_ERROR);
            mFragmentTransaction.add(R.id.root, mActivateSucceedFragment, "mActivateSucceedFragment");
        } else {
            mFragmentTransaction.add(R.id.root, mSimActivateFragment, "mSimActivateFragment");
        }
    }

    @Override
    public void initListener() {
        mActivateFailureFragment = new ActivateFailureFragment();
        mSimActivateFragment = new SimActivateFragment();
        mBuyFragment = new BuyFragment();
        mActivateSucceedFragment = new ActivateSucceedFragment().setSimState(ActivateSucceedFragment.ACTIVATE_SUCCEED);
        mActivateFailureFragment.setmActivateFailureListener(new ActivateFailureFragment.ActivateFailureListener() {
            @Override
            public void reStart() {
                showFragment(mSimActivateFragment, R.id.root);
            }

            @Override
            public void close() {
                onBackPressed();
            }
        });

        mActivateSucceedFragment.setActivateSucceedClickListener(new ActivateSucceedFragment.ActivateSucceedClickListener() {
            @Override
            public void setOnClick() {
//                showFragment(mActivateSucceedFragment,R.id.root);
//                MainActivity.this.startActivity(new Intent(MainActivity.this,MemberActivity.class));
//                MainActivity.this.finish();
//                showWebView();
                onBackPressed();
            }
        });
        mSimActivateFragment.setSimActivateClickListener(new SimActivateFragment.SimActivateClickListener() {

            private String userPhone;

            @Override
            public void showActivateState(String success) {
                if ("false".equals(success)) {
                    MainActivity.this.showFragment(mActivateFailureFragment, R.id.root);
                } else {
                    mActivateSucceedFragment.setSimState(ActivateSucceedFragment.ACTIVATE_SUCCEED);
                    MainActivity.this.showFragment(mActivateSucceedFragment, R.id.root);
                }
            }

            @Override
            public void setQRCodeClick() {
                mSimActivateFragment.showDialog(MainActivity.this);
            }

            @Override
            public void setUpdataRechargeUi(TextView mButton, ImageView mImageView) {
                mButton.setText(R.string.main_sim_activate_recharge_over);
                Data data = (Data) SharedPrefsUtil.getObjectValue(MainActivity.this, "user_info", null, Data.class);
                if (null != data) {
                    userPhone = data.getUserPhone();
                }
                mImageView.setImageBitmap(ZXingUtils.createQRImage(ApiComfig.URL_PAY +
                                "phone=" + ("0".equals(userPhone) || userPhone == null ? "0" : userPhone) +
                                "ismi=" + SimInfoUtils.getSimLine1Number(MainActivity.this),
                        mImageView.getWidth(), mImageView.getHeight()));
            }

            @Override
            public void showSucceedFragment() {
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
        initActivateState();
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

    private void showWebView() {
        root.addView(WebViewUtils.getWebViewInstance(this, ApiComfig.URL_TEST_HTTP));
    }

    private void initActivateState() {
        activated = SharedPrefsUtil.getValue(MainActivity.this, "activated", false);
    }

    @Override
    public void processClick(View v) {


    }

    private class ActivateStateServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mActivateStateServiceBinder = (PollingActivateStateService.ActivateStateServiceBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

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
