package com.xcc.bustraffic.bustraffic.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xcc.bustraffic.bustraffic.R;
import com.xcc.bustraffic.bustraffic.ui.fragment.MemberFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.ServiceFragment;
import com.xcc.bustraffic.bustraffic.ui.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by flykozhang on 2016/12/28.
 */

public class MemberActivity extends BaseActivity {
    @Bind(R.id.activity_member_left_title)
    TextView activityMemberLeftTitle;
    @Bind(R.id.activity_member_left_member)
    ImageView activityMemberLeftMember;
    @Bind(R.id.activity_member_left_service)
    ImageView activityMemberLeftService;
    @Bind(R.id.activity_member_left_setting)
    TextView activityMemberLeftSetting;
    @Bind(R.id.activity_member_left)
    LinearLayout activityMemberLeft;
    @Bind(R.id.activity_member_rghit)
    LinearLayout activityMemberRghit;
    private MemberFragment mMemberFragment;
    private ServiceFragment mServiceFragment;
    private SettingFragment mSettingFragment;

    @Override
    public int getLayoutId() {
        MobclickAgent.openActivityDurationTrack(false); //禁止默认的页面统计方式，这样将不会再自动统计Activity
        return R.layout.activity_member;
    }

    @Override
    public void initListener() {
        activityMemberLeftService.setOnClickListener(this);
        activityMemberLeftMember.setOnClickListener(this);
        activityMemberLeftSetting.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mMemberFragment = new MemberFragment();
        mServiceFragment = new ServiceFragment();
        mSettingFragment = new SettingFragment();

        mFragmentTransaction.add(R.id.activity_member_rghit, mMemberFragment, "mMemberFragment");
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.activity_member_left_service:  //联系客服
                if (mServiceFragment!=null){
                showFragment(mServiceFragment,R.id.activity_member_rghit);
                }
                break;
            case R.id.activity_member_left_member:  //会员服务
                if (mMemberFragment!=null){
                showFragment(mMemberFragment,R.id.activity_member_rghit);
                }
                break;
            case R.id.activity_member_left_setting:  //设置界面
                if (mSettingFragment!=null){
                    showFragment(mSettingFragment,R.id.activity_member_rghit);
                }
                break;
        }
    }
}
