package com.xcc.bustraffic.library.utils;

/**
 * @ClassName: ClickHelperUtils
 * @Description: 为了防止用户或者测试MM疯狂的点击某个button，写个方法防止按钮连续点击
 * @author 张鹏飞   QQ：83659757  
 *
 */
public class ClickHelperUtils {

    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();   
        if ( time - lastClickTime < 500) {   
            return true;   
        }   
        lastClickTime = time;   
        return false;   
    }
}

//使用方法：
//@Override
//public void onClick(View v) {
//    if (ClickHelperUtils.isFastClick()) {
//        return ;
//    }
//}