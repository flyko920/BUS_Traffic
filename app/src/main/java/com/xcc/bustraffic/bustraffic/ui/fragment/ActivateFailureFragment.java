package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xcc.bustraffic.bustraffic.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by flykozhang on 2016/12/27.
 */

public class ActivateFailureFragment extends BaseFragment {
    @Bind(R.id.activate_failure_button_restart)
    TextView activateFailureButtonRestart;
    @Bind(R.id.activate_failure_button_close)
    TextView activateFailureButtonClose;

    public ActivateFailureListener mActivateFailureListener;

    public interface ActivateFailureListener{
        void reStart();
        void close();
    }

    public void setmActivateFailureListener(ActivateFailureListener mActivateFailureListener) {
        this.mActivateFailureListener = mActivateFailureListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_activate_failure;
    }

    @Override
    public void initListener() {
        activateFailureButtonRestart.setOnClickListener(this);
        activateFailureButtonClose.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.activate_failure_button_restart:
                mActivateFailureListener.reStart();
                break;
            case R.id.activate_failure_button_close:
                mActivateFailureListener.close();
                break;

        }
    }

}
