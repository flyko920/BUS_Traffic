package com.xcc.bustraffic.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPref保存、读取，包含少数的基本属性的读取的工具类
 * SharedPrefsUtil
 */

public class SharedPrefsUtil {  
    public final static String SETTING = "xcc_user_info";
//    public final static String DOCTOR_STARTUP_UID = "feihua_doctor_startup_uid";
    
    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    需要保存的value值
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context,String key, int value) {  
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putInt(key, value);  
         sp.commit();  
    }
    
    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    需要保存的value值
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context,String key, boolean value) {  
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putBoolean(key, value);  
         sp.commit();  
    }  

    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    需要保存的value值
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context,String key, String value) {  
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putString(key, value);  
         sp.commit();  
    }  
    
    /**
     * 
     * @Title: putValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    需要保存的value值
     * @param @param file_name    要保存到的文件名
     * @return void    返回类型
     * @throws
     */
    public static void putValue(Context context,String key, String value,String file_name) {  
         Editor sp =  context.getSharedPreferences(file_name, Context.MODE_PRIVATE).edit();  
         sp.putString(key, value);  
         sp.commit();  
    }  
    
    public static void putValue(Context context,String key, Long value) {  
        Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
        sp.putLong(key, value);  
        sp.commit();  
   } 
    
    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    获取失败，显示的默认值
     * @return void    返回类型
     * @throws
     */
    public static int getValue(Context context,String key, int defValue) {  
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        int value = sp.getInt(key, defValue);  
        return value;  
    }
    
    /**
    * @Title: getValue 
    * @Description: 获取long型的返回值
    * @param @param context
    * @param @param key
    * @param @param defValue
    * @param @return    设定文件 
    * @return long    返回类型 
    * @throws 
    */
    public static long getValue(Context context,String key, long defValue) {  
    	SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
    	long value = sp.getLong(key, defValue);  
    	return value;  
    }

    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    获取失败，显示的默认值
     * @return void    返回类型
     * @throws
     */
    public static boolean getValue(Context context,String key, boolean defValue) {  
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        boolean value = sp.getBoolean(key, defValue);  
        return value;  
    } 

    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    获取失败，显示的默认值
     * @return void    返回类型
     * @throws
     */
    public static String getValue(Context context,String key, String defValue) {  
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        String value = sp.getString(key, defValue);  
        return value;  
    }

    /**
     * 
     * @Title: getValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param key	需要保存的Key值
     * @param @param value    获取失败，显示的默认值
     * @param @param file_name    从改文件名的文件中取数据
     * @return void    返回类型
     * @throws
     */
    public static String getValue(Context context,String key, String defValue,String file_name ) {  
    	SharedPreferences sp =  context.getSharedPreferences(file_name, Context.MODE_PRIVATE);  
    	String value = sp.getString(key, defValue);  
    	return value;  
    }
    
	/**
	 * 
	 * @Title: clearData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param context UI上下文
	 * @param @param filename    需要清空的文件名
	 * @return void    返回类型
	 * @throws
	 */
    public static void clearData(Context context) {
    	  SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
    	  sp.edit().clear().commit();
    }
    /**
     * 
     * @Title: clearData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param context UI上下文
     * @param @param filename    需要清空的文件名
     * @return void    返回类型
     * @throws
     */
    public static void clearData(Context context,String file_name ) {
    	SharedPreferences sp =  context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
    	sp.edit().clear().commit();
    }
    
    /** 
    * @Title: clearDataByKey 
    * @Description: 删除哪一条数据 
    * @param @param context
    * @param @param file_name
    * @param @param key    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public static void clearDataByKey (Context context,String file_name,String key ) {
    	SharedPreferences sp =  context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
    	sp.edit().remove(key).commit();
    }
}  