package com.xcc.bustraffic.bustraffic.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.ui.callbcak.MyActivateSucceedClickListener;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateSucceedFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.BaseFragment;
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
    private boolean isFirst;
    private boolean activated;


    @Override
    public int getLayoutId() {
        MobclickAgent.openActivityDurationTrack(false); //禁止默认的页面统计方式，这样将不会再自动统计Activity
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        mSimActivateFragment = new SimActivateFragment();
        mActivateSucceedFragment = new ActivateSucceedFragment();
        mActivateSucceedFragment.setActivateSucceedClickListener(new MyActivateSucceedClickListener(this));
        mSimActivateFragment.setSimActivateClickListener(new SimActivateFragment.SimActivateClickListener() {
            @Override
            public void setQRCodeClick() {
                mSimActivateFragment.showDialog(MainActivity.this);
            }

            @Override
            public void setUpdataRechargeUi(TextView mButton, ImageView mImageView) {
                mButton.setText(R.string.main_sim_activate_recharge_over);
                mImageView.setImageBitmap(ZXingUtils.createQRImage(ApiComfig.URL_PAY+
                        "phone="+SharedPrefsUtil.getValue(MainActivity.this,"phone","0")+
                        "ismi="+SimInfoUtils.getSimLine1Number(MainActivity.this),
                        mImageView.getWidth(),mImageView.getHeight()));
            }

            @Override
            public void showSucceedFragment() {
                showFragment(mActivateSucceedFragment);
            }
        });
    }

    public void showFragment(BaseFragment mFragment){
//        toast(mFragment.getClass().getSimpleName());
        toast(SimInfoUtils.getSimSerialNumber(this));
        mSupportFragmentManager.
                beginTransaction().
                replace(R.id.root, mFragment, mFragment.getClass().getSimpleName()).
                addToBackStack(null).
                commit();
    }

    @Override
    public void initData() {
        initActiviateState();
        if (!isFirst && activated) {
            showWebView();
        } else {
            mFragmentTransaction.add(R.id.root, mSimActivateFragment, "mSimActivateFragment");
        }
    }

    private void showWebView() {
        root.addView(WebViewUtils.getWebViewInstance(this, ApiComfig.URL_TEST_HTTP));
    }

    private void initActiviateState() {
        activated = SharedPrefsUtil.getValue(MainActivity.this, "activated", false);
        isFirst = SharedPrefsUtil.getValue(MainActivity.this, "isFirst", false);
    }

    @Override
    public void processClick(View v) {

    }
}
