package com.xcc.bustraffic.bustraffic.api.callback;

import android.view.View;
import android.widget.ProgressBar;

import com.xcc.bustraffic.library.utils.L;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by flykozhang on 2016/12/16.
 */

public class BastCallBack<T> implements Callback<T> {
    private ProgressBar progressBar;
    public String errmsg = "";
    public final String TAG = this.getClass().getSimpleName();

    public BastCallBack(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        L.i(TAG,"onResponse == " + response.body().toString());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        L.i(TAG,"onFailure 。。。。。。。" );
        if(progressBar != null){
            progressBar.setVisibility(View.INVISIBLE);
        }
        L.d(errmsg);
    }
}
