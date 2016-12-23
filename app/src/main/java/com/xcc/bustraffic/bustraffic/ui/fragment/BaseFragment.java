package com.xcc.bustraffic.bustraffic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.ui.UIInterface;
import com.xcc.bustraffic.library.utils.ClickHelperUtils;
import com.xcc.bustraffic.library.utils.ZXingUtils;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment implements View.OnClickListener , UIInterface {

    private View view;
    private String UMFragmentTag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), getLayoutId(), null);
        ButterKnife.bind(this, view);
        initListener();
        initData();
        regCommonBtn();
        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(UMFragmentTag); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(UMFragmentTag);
    }

    /** 返回viewId引用的view */
    protected View findViewById(int viewId) {
        return view.findViewById(viewId);
    }

    /** 在多个界面间都存在的按钮，点击事件已经由Base处理，那么将点击事件注册也统一处理掉 */
    private void regCommonBtn() {
        View view = findViewById(R.id.back);
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        //避免快速点击事件
        if (ClickHelperUtils.isFastClick()) {
            return ;
        }
        // 把在多个界面间都存在的点击，统一处理掉
        switch (v.getId()){
            case R.id.back:
                getFragmentManager().popBackStack();
                break;
            default:
                processClick(v);
                break;
        }
    }

    /**给fragment打上友盟统计页面数据的TAG*/
    protected void setUMFragmentTag(String mUMFragmentTag){
        this.UMFragmentTag = mUMFragmentTag;
    }

    /** 显示一个内容为msg的吐司 */
    protected void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /** 显示一个内容为msgid引用的string的吐司 */
    protected void toast(int msgId) {
        Toast.makeText(getActivity(), msgId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 从一个url中动态显示一个二维码
     * @param view  二维码显示的imageview
     */
    protected void shwoQRcode(final ImageView view, final String url){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                view.setImageBitmap(ZXingUtils.createQRImage(url, view.getWidth(), view.getHeight()));
            }
        });
    }
}
