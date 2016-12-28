package com.xcc.bustraffic.bustraffic.view.res;

import com.xcc.bustraffic.bustraffic.R;

import java.lang.reflect.Array;

/**
 * Created by flykozhang on 2016/12/28.
 */

public class ResourceArray {

    public static int getSimStateTitleRes(int mSimState){
        int[] titleArray = new int[]{R.string.activate_succeed_titile,R.string.activate_succeed_no_sim};
        return titleArray[mSimState];
    }

    public static int getSimStateSubTitleRes(int mSimState) {
        int[] titleArray = new int[]{R.string.activate_succeed_sub_titile,R.string.activate_succeed_sub_titile_no_sim};
        return titleArray[mSimState];
    }
}
