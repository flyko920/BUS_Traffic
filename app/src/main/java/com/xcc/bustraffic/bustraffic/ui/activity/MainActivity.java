package com.xcc.bustraffic.bustraffic.ui.activity;

import android.view.View;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.ui.fragment.SimActivateFragment;



public class MainActivity extends BaseActivity {

    private SimActivateFragment mSimActivateFragment;


    @Override
    public int getLayoutId() {
        MobclickAgent.openActivityDurationTrack(false); //禁止默认的页面统计方式，这样将不会再自动统计Activity
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        mSimActivateFragment = new SimActivateFragment();
        mSimActivateFragment.setSimActivateClickListener(new SimActivateFragment.SimActivateClickListener() {
            @Override
            public void setQRCodeClick() {
                mSimActivateFragment.showDialog(MainActivity.this);
            }
            @Override
            public void setUpdataRechargeUi(Button mButton) {
                mButton.setText(R.string.main_sim_activate_recharge_over);
            }
        });
    }

    @Override
    public void initData() {
        mFragmentTransaction.add(R.id.root, mSimActivateFragment,"mSimActivateFragment");
    }

    @Override
    public void processClick(View v) {

    }



}
