package com.xcc.bustraffic.bustraffic.api.callback;

import android.view.View;
import android.widget.ProgressBar;

import com.xcc.bustraffic.bustraffic.bean.BastBean;
import com.xcc.bustraffic.library.utils.L;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by flykozhang on 2016/12/16.
 */
public class BastBeanCallBack extends BastCallBack<BastBean> {

    private final ProgressBar mProgressBar;

    /**
     * 可以通过参数把UI的VIEW传到Callback中进行处理
     * @param progressBar
     */
    public BastBeanCallBack(ProgressBar progressBar) {
        super(progressBar);
        this.mProgressBar = progressBar;
    }

    @Override
    public void onResponse(Call<BastBean> call, Response<BastBean> response) {
        super.onResponse(call, response);
        if(mProgressBar != null){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
        L.e("2222","country:" + response.body().toString());
    }


}
