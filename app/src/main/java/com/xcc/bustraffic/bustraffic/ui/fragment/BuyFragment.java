package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.comfig.ApiComfig;
import com.xcc.bustraffic.library.utils.SimInfoUtils;

import butterknife.Bind;

/**
 * Created by flykozhang on 2016/12/30.
 */

public class BuyFragment extends BaseFragment {
    @Bind(R.id.buy_imageview)
    ImageView buyImageview;
    @Bind(R.id.sim_activate_titile)
    TextView simActivateTitile;
    @Bind(R.id.buy_button)
    TextView buyButton;
    private String mPackageDay;
    private BuyFragmentListener mBuyFragmentListener;

    public interface BuyFragmentListener {
        void okOnClick();
    }

    public void setBuyFragmentListener(BuyFragmentListener mBuyFragmentListener) {
        this.mBuyFragmentListener = mBuyFragmentListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_buy;
    }

    @Override
    public void initListener() {
        buyButton.setOnClickListener(this);
    }


    @Override
    public void initData() {
        showBuyQrcode();
        setTitle();
    }

    private void setTitle() {
        if (mPackageDay != null) {
            simActivateTitile.setText(getString(R.string.fragment_buy_1) + mPackageDay + getString(R.string.fragment_buy_2));
        }
    }

    /*显示充值二维码*/
    private void showBuyQrcode() {
//        Data data = (Data) SharedPrefsUtil.getObjectValue(getActivity(), "user_info", "", Data.class);
//        String userPhone = null;
//        if (data != null) {
//            userPhone = data.getUserPhone();
//        }
//        shwoQRcode(buyImageview, ApiComfig.URL_PAY + "phone=" +
//                (userPhone == null ? "" : userPhone) + "imsi=" + SimInfoUtils.getSimSerialNumber(getActivity()));  //梦思要求，当没有手机号码或者ismi号码时，给空
        shwoQRcode(buyImageview, ApiComfig.URL_PAY + "imsi=" + SimInfoUtils.getSimSerialNumber(getActivity()));  //梦思要求，当没有手机号码或者ismi号码时，给空
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.buy_button:
                mBuyFragmentListener.okOnClick();
                break;
        }
    }

    public void setTitle(String packageDay) {
        this.mPackageDay = packageDay;
    }

}
