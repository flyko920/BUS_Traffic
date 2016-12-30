package com.xcc.bustraffic.bustraffic.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.xcc.bustraffic.bustraffic.R;

public class SplashActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void processClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        // 延迟跳转到主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
