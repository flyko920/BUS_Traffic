package com.xcc.bustraffic.bustraffic.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.api.NetApi;
import com.xcc.bustraffic.bustraffic.api.callback.BastCallBack;
import com.xcc.bustraffic.bustraffic.bean.DataVO;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.service.PollingActivateStateIntentService;
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
    private ActivateFailureFragment mActivateFailureFragment;

    private ActivateStateReceiver mActivateStateReceiver;
    private BuyFragment mBuyFragment;
    private String packageDay;

    private class ActivateStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            showFragment(mActivateSucceedFragment, R.id.root);
            L.i(TAG, "onReceive...............");

            new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    return null;
                }
            };
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
//            mFragmentTransaction.add(R.id.root, mActivateSucceedFragment, "mActivateSucceedFragment");
            showFragment(mActivateSucceedFragment, R.id.root);
        } else {
//            mFragmentTransaction.add(R.id.root, mSimActivateFragment, "mSimActivateFragment");
            showFragment(mSimActivateFragment, R.id.root);
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
        L.i(TAG, "updateMode..............."+response.body().toString());
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

    private void showWebView() {
        L.i(TAG, "showWebView...............");
        root.addView(WebViewUtils.getWebViewInstance(this, ApiComfig.URL_MEMBER + "imsi=" + SimInfoUtils.getSimSerialNumber(this)));
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
        L.i(TAG, "showSuitablePage...............");
        L.i(TAG, response.body().toString());
        if (null != response.body().getData()) {
            packageDay = response.body().getData().get(0).getPackageDay();
        }
        if ("true".equals(response.body().isSuccess() + "")) {                                                            // SIM卡已经激活成功
            SharedPrefsUtil.putObjectValue(MainActivity.this, "user_info", response.body().getData().get(0));
            if (ApiComfig.PACKAGE_DAY > Integer.parseInt(packageDay) && Integer.parseInt(packageDay) > 0) {               //小于设定值，显示提醒用户当前剩余天数
                showWebView();
                mSimActivateFragment.showDialog(MainActivity.this, packageDay);
            } else if ("0".equals(packageDay)) {                                                                          // 小于0天，服务到期，提示充值页面
                showFragment(mBuyFragment, R.id.root);                                                                    //提示充值页面
            } else {
                showWebView();                                                                                            // 显示会员H5页面
            }
        } else {                                                                                                          // SIM卡未激活,显示SIM卡激活页面，并开启轮询服务
//            Intent intent = new Intent("com.xcc.bustraffic.bustraffic.service");
            Intent intent = new Intent(this, PollingActivateStateIntentService.class);
            startService(intent);                                                                                         //开启轮询服务
            showSimActivateFragment();                                                                                    //显示SIM卡激活页面
        }
    }

    @Override
    public void processClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mActivateStateReceiver);
    }
}
