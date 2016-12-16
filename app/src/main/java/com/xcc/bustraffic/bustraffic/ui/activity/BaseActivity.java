package com.xcc.bustraffic.bustraffic.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.ui.UIInterface;
import com.xcc.bustraffic.library.utils.ClickHelperUtils;

import butterknife.ButterKnife;

/**
 * 基类
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener , UIInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
//        initView();
        initListener();
        initData();
        regCommonBtn();
    }

    /** 在多个界面间都存在的按钮，点击事件已经由Base处理，那么将点击事件注册也统一处理掉 */
    private void regCommonBtn() {
        View view = findViewById(R.id.back);
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        //避免快速点击事件
        if (ClickHelperUtils.isFastClick()) {
            return ;
        }
        // 把在多个界面间都存在的点击，统一处理掉
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            default:
                processClick(v);
                break;
        }
    }

    /** 显示一个内容为msg的吐司 */
    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /** 显示一个内容为msgid引用的string的吐司 */
    protected void toast(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }
}
