package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.view.XCCDialog;
import com.xcc.bustraffic.library.utils.WebViewUtils;

import butterknife.Bind;

/**
 * Created by flykozhang on 2016/12/22.
 */

public class SimActivateFragment extends BaseFragment {

    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.button2)
    Button button2;

    private SimActivateClickListener mSimActivateClickListener;

    public interface SimActivateClickListener {
        void setQRCodeClick();
        void setUpdataRechargeUi(Button mButton);
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
        imageView3.setOnClickListener(this);
        button3.setOnClickListener(this);
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
            case R.id.imageView3:
                mSimActivateClickListener.setQRCodeClick();
                break;
            case R.id.button3:
                activityMain.addView(WebViewUtils.getWebViewInstance(getActivity(), ApiComfig.URL_TEST_HTTP));
                break;
        }
    }

    public void showDialog(final Activity activity) {
        if (mSimActivateClickListener == null) {
            return;
        }
        XCCDialog.showDialog(activity, mSimActivateClickListener, button3);
    }


}
