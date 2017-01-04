package com.xcc.bustraffic.bustraffic.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

import com.xcc.bustraffic.bustraffic.R;

/**
 * 强制更新的dialog窗口，不可以关闭
 * Created by flykozhang on 2017/1/4.
 */
public class ForcedUpdatingDialog {
    public static void showDialog(Context mContext){
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(mContext);
        localBuilder.setMessage(R.string.dialog_forced_updata_msg);
        localBuilder.setTitle("版本更新");
        localBuilder.setCancelable(false);
        localBuilder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH){
                    return true;
                }
                return false;
            }
        });
        localBuilder.create().show();
    }
}
