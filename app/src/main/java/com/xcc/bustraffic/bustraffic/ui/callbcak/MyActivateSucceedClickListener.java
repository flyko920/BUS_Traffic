package com.xcc.bustraffic.bustraffic.ui.callbcak;

import com.xcc.bustraffic.bustraffic.ui.activity.BaseActivity;
import com.xcc.bustraffic.bustraffic.ui.activity.MainActivity;
import com.xcc.bustraffic.bustraffic.ui.fragment.ActivateSucceedFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.BaseFragment;

/**
 * Created by flykozhang on 2016/12/27.
 */

public class MyActivateSucceedClickListener implements ActivateSucceedFragment.ActivateSucceedClickListener {


    private final MainActivity mMainActivity;

    public MyActivateSucceedClickListener(BaseActivity mBaseActivity) {
        this.mMainActivity=(MainActivity)mBaseActivity;
    }

    @Override
    public void setOnClick(BaseFragment mBaseFragment) {
        mMainActivity.showFragment(mBaseFragment);
    }
}
