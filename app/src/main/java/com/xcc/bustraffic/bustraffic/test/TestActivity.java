package com.xcc.bustraffic.bustraffic.test;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.api.NetApi;
import com.xcc.bustraffic.bustraffic.api.callback.BastBeanCallBack;
import com.xcc.bustraffic.bustraffic.ui.activity.BaseActivity;
import com.xcc.bustraffic.library.utils.L;
import com.xcc.bustraffic.library.utils.ZXingUtils;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends BaseActivity {

    private static final String URL_TEST_HTTP = "http://192.168.0.101:8080/bus/services/api/weiXinPermission/getToken";
    private static final String URL_TEST_INMAGE = "http://g.hiphotos.baidu.com/baike/w%3D268%3Bg%3D0/sign=ad7e701b7ef0f736d8fe4b07326ed424/3801213fb80e7bec52fd4585292eb9389b506baa.jpg";

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.activity_test)
    RelativeLayout activityTest;
    @Bind(R.id.imageView)
    ImageView imageView;

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
        button.setText("测试哈哈哈哈啊哈");
    }

    @Override
    public void processClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                run5();
                button.setText("点击变化了哦····");
//                imageView.setImageBitmap(ZXingUtils.createQRImage(URL_TEST_HTTP,imageView.getWidth(),imageView.getHeight()));//在imageView中显示二维码图片
                Picasso.with(mContext).load(URL_TEST_INMAGE).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);
                break;
            default:
                break;
        }
    }


    private void run5() {
        NetApi.getFeed(new BastBeanCallBack(null));
    }







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
