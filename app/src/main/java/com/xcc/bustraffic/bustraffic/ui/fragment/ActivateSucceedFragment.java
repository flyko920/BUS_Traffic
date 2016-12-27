package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.xcc.bustraffic.bustraffic.R;

import butterknife.Bind;

/**
 * Created by flykozhang on 2016/12/27.
 */

public class ActivateSucceedFragment extends BaseFragment {
    @Bind(R.id.activate_succeed_button_konw)
    TextView activateSucceedButtonKonw;
    private ActivateSucceedClickListener mActivateSucceedClickListener;

    public interface ActivateSucceedClickListener {
        void setOnClick(BaseFragment mBaseFragment);
    }

    public void setActivateSucceedClickListener(ActivateSucceedClickListener mActivateSucceedClickListener) {
        this.mActivateSucceedClickListener = mActivateSucceedClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_activate_succeed;
    }

    @Override
    public void initListener() {
        activateSucceedButtonKonw.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.activate_succeed_button_konw:
                mActivateSucceedClickListener.setOnClick(this);
                break;
        }
    }



}
