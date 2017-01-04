package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.api.NetApi;
import com.xcc.bustraffic.bustraffic.api.callback.BastCallBack;
import com.xcc.bustraffic.bustraffic.bean.BastBean;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.bustraffic.view.XCCDialog;
import com.xcc.bustraffic.library.utils.SharedPrefsUtil;
import com.xcc.bustraffic.library.utils.SimInfoUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

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
    private BastCallBack<BastBean> getUserInfoCallBack;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface SimActivateClickListener {
        void setQRCodeClick();

        void setUpdataRechargeUi(TextView mButton,ImageView mImageView);

        void showSucceedFragment();

        void showActivateState(String success);
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
        shwoQRcode(sim_activate_imageview, ApiComfig.URL_ACTIVATE+"imsi="+ SimInfoUtils.getSimSerialNumber(getActivity())+"&imei="+SimInfoUtils.getDeviceId(getActivity()));
        setUMFragmentTag("SimActivateFragment");
        getUserInfoCallBack = new BastCallBack<BastBean>(null) {
            @Override
            public void onResponse(Call<BastBean> call, Response<BastBean> response) {
                super.onResponse(call, response);
                if("true".equals(response.body().isSuccess() + "")){
                    SharedPrefsUtil.putObjectValue(getActivity(),"user_info",response.body().getData().get(0));
                }
                mSimActivateClickListener.showActivateState(response.body().isSuccess() + "");
            }

            @Override
            public void onFailure(Call<BastBean> call, Throwable t) {
                super.onFailure(call, t);
            }
        };
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
                NetApi.getUserActivateInfo(getUserInfoCallBack,SimInfoUtils.getSimSerialNumber(getActivity()));
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
        XCCDialog.showDialog(activity, mSimActivateClickListener, sim_activate_buttom_left,sim_activate_imageview);
    }


}
