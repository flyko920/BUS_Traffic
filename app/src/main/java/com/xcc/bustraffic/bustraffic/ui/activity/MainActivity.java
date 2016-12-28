package com.xcc.bustraffic.bustraffic.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateSucceedFragment;
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

    private void showSimErrorOrActivate() {
        if(SimInfoUtils.getSimSerialNumber(this)==null){
            mActivateSucceedFragment.setSimState(ActivateSucceedFragment.SIM_ERROR);
            mFragmentTransaction.add(R.id.root, mActivateSucceedFragment, "mActivateSucceedFragment");
        }else {
            mFragmentTransaction.add(R.id.root, mSimActivateFragment, "mSimActivateFragment");
        }
    }

    @Override
    public void initListener() {
        mSimActivateFragment = new SimActivateFragment();
        mActivateSucceedFragment = new ActivateSucceedFragment().setSimState(ActivateSucceedFragment.ACTIVATE_SUCCEED);
        mActivateSucceedFragment.setActivateSucceedClickListener(new ActivateSucceedFragment.ActivateSucceedClickListener() {
            @Override
            public void setOnClick() {
//                showFragment(mActivateSucceedFragment,R.id.root);
                MainActivity.this.startActivity(new Intent(MainActivity.this,MemberActivity.class));
                MainActivity.this.finish();
            }
        });
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
                showFragment(mActivateSucceedFragment,R.id.root);
            }
        });
    }



    @Override
    public void initData() {
        initActiviateState();

        if (!isFirst && activated) {
            showWebView();
        } else {
            showSimErrorOrActivate();

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
