package com.xcc.bustraffic.library.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 获取手机信息工具类
 * Created by flykozhang on 2016/12/28.
 */
public class SimInfoUtils {

    //获取电话号码
    public static String getSimLine1Number(Context mContext) {
        TelephonyManager mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getLine1Number();
    }

    //获取SIM卡的序列号  IMSI 号
    public static String getSimSerialNumber(Context mContext) {
        TelephonyManager mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getSimSerialNumber();

    }

    //获取设备编号
    public static String getDeviceId(Context mContext) {
        TelephonyManager mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getDeviceId();
    }
}
