package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xcc.bustraffic.bustraffic.R;

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
        void QRCodeClick();
        void UpdataRechargeUi();
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
//                dialog1();
                mSimActivateClickListener.QRCodeClick();
                break;
            default:
                break;
        }
//        if(mSimActivateClickListener != null){
//            mSimActivateClickListener.QRCodeClick();
//        }
    }



    private void updataRechargeUi(String text) {
        button3.setText(text);
    }
}
