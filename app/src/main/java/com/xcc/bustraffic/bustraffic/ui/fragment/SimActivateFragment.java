package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.view.XCCDialog;
import com.xcc.bustraffic.library.utils.WebViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by flykozhang on 2016/12/22.
 */

public class SimActivateFragment extends BaseFragment {

    @Bind(R.id.sim_activate_imageview)
    ImageView sim_activate_imageview;
    @Bind(R.id.sim_activate_buttom_left)
    TextView sim_activate_buttom_left;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    @Bind(R.id.sim_activate_buttom_right)
    TextView simActivateButtomRight;

    private SimActivateClickListener mSimActivateClickListener;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface SimActivateClickListener {
        void setQRCodeClick();

        void setUpdataRechargeUi(TextView mButton);

        void showSucceedFragment();
    }

    public void setSimActivateClickListener(SimActivateClickListener simActivateClickListener) {
        this.mSimActivateClickListener = simActivateClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sim_activate;
    }

    @Override
    public void initListener() {
        sim_activate_imageview.setOnClickListener(this);
        sim_activate_buttom_left.setOnClickListener(this);
        simActivateButtomRight.setOnClickListener(this);
    }

    @Override
    public void initData() {
//        shwoQRcode(imageView3, ApiComfig.URL_TEST_HTTP);
        setUMFragmentTag("SimActivateFragment");
    }

    @Override
    public void processClick(View v) {
        if (mSimActivateClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.sim_activate_imageview:
                mSimActivateClickListener.setQRCodeClick();
                break;
            case R.id.sim_activate_buttom_left:
                activityMain.addView(WebViewUtils.getWebViewInstance(getActivity(), ApiComfig.URL_TEST_HTTP));
                break;
            case R.id.sim_activate_buttom_right:
                mSimActivateClickListener.showSucceedFragment();
                break;
        }
    }

    public void showDialog(final Activity activity) {
        if (mSimActivateClickListener == null) {
            return;
        }
        XCCDialog.showDialog(activity, mSimActivateClickListener, sim_activate_buttom_left);
    }


}
