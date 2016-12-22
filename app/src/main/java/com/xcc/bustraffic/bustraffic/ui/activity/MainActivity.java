package com.xcc.bustraffic.bustraffic.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.button3)
    Button button3;

    @Override
    public int getLayoutId() {
        MobclickAgent.openActivityDurationTrack(false); //禁止默认的页面统计方式，这样将不会再自动统计Activity
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        imageView3.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.imageView3:
                dialog1();
                break;
            default:
                break;
        }
    }

    private void dialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setMessage("你的小车车还有10天到期，后期后将无法享受上网服务，请及时充值哦！"); //设置内容
        builder.setPositiveButton("充值", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Toast.makeText(MainActivity.this, "充值" + which, Toast.LENGTH_SHORT).show();
                updataRechargeUi();
            }
        });
        builder.setNegativeButton("暂不需要", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "暂不需要" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    private void updataRechargeUi() {
        button3.setText("已充值");
    }
}
