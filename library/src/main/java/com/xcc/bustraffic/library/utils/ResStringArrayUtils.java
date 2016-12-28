package com.xcc.bustraffic.library.utils;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by flykozhang on 2016/12/28.
 */

public class ResStringArrayUtils {

    public static int getResId(Context mContext,int resArrayId,int index){
        TypedArray typedArray = mContext.getResources().obtainTypedArray(resArrayId);
        return typedArray.getResourceId(index,-1);
    }
}
