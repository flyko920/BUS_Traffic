package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.bean.Data;
import com.xcc.bustraffic.library.utils.L;
import com.xcc.bustraffic.library.utils.ResStringArrayUtils;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by flykozhang on 2016/12/27.
 */

public class ActivateSucceedFragment extends BaseFragment {
    public static final int ACTIVATE_SUCCEED = 0;
    public static final int SIM_ERROR = 1;

    @Bind(R.id.activate_succeed_button_konw)
    TextView activateSucceedButtonKonw;
    @Bind(R.id.activate_succeed_titile)
    TextView activateSucceedTitile;
    @Bind(R.id.activate_succeed_sub_titile)
    TextView activateSucceedSubTitile;

    private int mSimState;

    public ActivateSucceedFragment setSimState(int mSimState){
        this.mSimState = mSimState;
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    private ActivateSucceedClickListener mActivateSucceedClickListener;

    public interface ActivateSucceedClickListener {
        void setOnClick();
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
        activateSucceedTitile.setText(ResStringArrayUtils.getResId(getActivity(),R.array.SimStateTitleRes,mSimState));
        activateSucceedSubTitile.setText(ResStringArrayUtils.getResId(getActivity(),R.array.SimStateSubTitleRes,mSimState));
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.activate_succeed_button_konw:
                if (1==mSimState){
                    getActivity().onBackPressed();
                }
                mActivateSucceedClickListener.setOnClick();
                break;
        }
    }


}
