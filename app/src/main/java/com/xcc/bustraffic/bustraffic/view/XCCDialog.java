package com.xcc.bustraffic.bustraffic.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.ui.fragment.SimActivateFragment;

/**
 * Created by flykozhang on 2016/12/23.
 */

public class XCCDialog {

    public static void showDialog(final Activity mActivity, final SimActivateFragment.SimActivateClickListener mSimActivateClickListener, final TextView button, final ImageView mImageView){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);  //先得到构造器
        builder.setMessage(R.string.main_sim_activate_title); //设置内容
        builder.setPositiveButton(R.string.main_sim_activate_recharge, new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Toast.makeText(mActivity, R.string.main_sim_activate_recharge + which, Toast.LENGTH_SHORT).show();
                mSimActivateClickListener.setUpdataRechargeUi(button,mImageView);
            }
        });
        builder.setNegativeButton(R.string.main_activate_false, new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(mActivity, R.string.main_activate_false + which, Toast.LENGTH_SHORT).show();
                mActivity.onBackPressed();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
}
