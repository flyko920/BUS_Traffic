package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.ui.activity.MainActivity;
import com.xcc.bustraffic.bustraffic.view.XCCDialog;

import butterknife.Bind;

/**
 * Created by flykozhang on 2016/12/22.
 */

public class SimActivateFragment extends BaseFragment {

    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.button3)
    Button button3;

    private SimActivateClickListener mSimActivateClickListener;

    public interface SimActivateClickListener{
        void setQRCodeClick();
        void setUpdataRechargeUi(Button mButton);
    }

    public void setSimActivateClickListener(SimActivateClickListener simActivateClickListener){
        this.mSimActivateClickListener = simActivateClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sim_activate;
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
        if(mSimActivateClickListener==null){
            return;
        }
        switch (v.getId()) {
            case R.id.imageView3:
                mSimActivateClickListener.setQRCodeClick();
                break;
            default:
                break;
        }

    }

    public void showDialog(final Activity activity) {
        XCCDialog.showDialog(activity,mSimActivateClickListener,button3);
    }

}
