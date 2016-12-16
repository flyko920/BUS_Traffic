package com.xcc.bustraffic.bustraffic.test;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.api.NetApi;
import com.xcc.bustraffic.bustraffic.api.callback.BastBeanCallBack;
import com.xcc.bustraffic.bustraffic.ui.activity.BaseActivity;

import butterknife.Bind;

public class TestActivity extends BaseActivity {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.toggleButton)
    ToggleButton toggleButton;
    @Bind(R.id.checkBox)
    CheckBox checkBox;
    @Bind(R.id.activity_test)
    RelativeLayout activityTest;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initListener() {
        button.setOnClickListener(this);
    }

    @Override
    public void initData() {
        run5();
        button.setText("测试哈哈哈哈啊哈");
    }

    @Override
    public void processClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                button.setText("点击变化了哦····");
                break;
            default:
                break;
        }
    }


    private void run5() {
        NetApi.getFeed(new BastBeanCallBack(null));
    }


























//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//        run5();
//    }
//
//
//    public void run(){
//        // 构建Retrofit实例
//        Retrofit retrofit = new Retrofit.Builder().
//                baseUrl(API2).
//                addConverterFactory(GsonConverterFactory.create()).
//                build();
//        // 构建接口的实现类
//        NetApi weatherAPI = retrofit.create(NetApi.class);
//        // 调用接口定义的方法
//        Call<BastBean> weatherCall = weatherAPI.getFeed();
//        // 异步执行请求
//        weatherCall.enqueue(new Callback<BastBean>() {
//            @Override
//            public void onResponse(Call<BastBean> call, Response<BastBean> response) {
//                BastBean model = response.body();
//                Log.e("2222","country:" + model.getErrorMsg());
//            }
//
//            @Override
//            public void onFailure(Call<BastBean> call, Throwable t) {
//                Log.e("2222",t.toString());
//            }
//        });
//
//    }
//
//    public void run2(){
//        NetApi mApiHelper = (NetApi) new ApiHelper<NetApi>().getInstance(NetApi.class);
//        Call<BastBean> mCall = mApiHelper.getFeed();
//        mCall.enqueue(new Callback<BastBean>() {
//            @Override
//            public void onResponse(Call<BastBean> call, Response<BastBean> response) {
//                BastBean model = response.body();
//                Log.e("2222","country:" + model.toString());
//            }
//
//            @Override
//            public void onFailure(Call<BastBean> call, Throwable t) {
//                Log.e("2222",t.toString());
//            }
//        });
//    }
//
//    public void run3(){
//        BastBeanApi.getFeed(new Callback<BastBean>() {
//            @Override
//            public void onResponse(Call<BastBean> call, Response<BastBean> response) {
//                BastBean model = response.body();
//                Log.e("2222","country:" + model.toString());
//            }
//
//            @Override
//            public void onFailure(Call<BastBean> call, Throwable t) {
//                Log.e("2222",t.toString());
//            }
//        });
//    }
//
//    private void run4(){
//        BastBeanApi.getFeed(new BastCallBack<BastBean>(null){
//            @Override
//            public void onResponse(Call<BastBean> call, Response<BastBean> response) {
//                BastBean model = response.body();
//                Log.e("2222","country:" + model.toString());
//            }
//
//            @Override
//            public void onFailure(Call<BastBean> call, Throwable t) {
//                Log.e("2222",t.toString());
//            }
//        });
//    }
}
