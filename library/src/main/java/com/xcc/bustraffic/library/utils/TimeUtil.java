package com.xcc.bustraffic.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;


public class TimeUtil {

	/**
	 * 判断时间，若返回值=0则time1和time2相等，time1>time2则返回值大于零
	 */
	public static int compareTime(String time1, String time2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1, d2;
		int compareTo = 0;
		try {
			d1 = df.parse(time1);
			d2 = df.parse(time2);
			compareTo = d1.compareTo(d2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return compareTo;
	}

	/**
	 * 将秒转换成年月日格式
	 * 
	 * @param time
	 * @return返回时间格式yyyy-MM-dd
	 */
	public static String getStrTime(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		// long loc_time = Long.valueOf(time);
		long loc_time = Long.parseLong(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}
	/**
	 * 将秒转换成月日格式
	 * 
	 * @param time
	 * @return返回时间格式MM-dd
	 */
	public static String getStrTimeMMdd(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("MM-dd");
		// long loc_time = Long.valueOf(time);
		long loc_time = Long.parseLong(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}
	/**
	 * 将秒转换成月日格式
	 * @param time
	 * @return返回时间格式MM-dd
	 */
	public static String getStrTimeEEE(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("E");
		long loc_time = Long.parseLong(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 获得yyyy-MM-dd HH:mm格式
	 * 
	 * @param time
	 * @return
	 */

	public static String getStrTime2(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		if (time == null) {
			return null;
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 获得yyyy年MM月dd日 HH:mm格式
	 * 
	 * @param time
	 * @return
	 */
	public static String getStrTime2_1(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time));
		return re_StrTime;
	}

	public static String getStrTime2_1_1(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * @Title: getFutureTime
	 * @Description: 获取某时间点的多少分钟后的时间值: 格式 yyyy年MM月dd日 HH:mm
	 * @param time
	 *            指定时间值 时间戳
	 * @param minute
	 *            指定时间间隔 分钟数 例如：30分钟
	 * @return String 返回类型
	 */
	public static String getFutureTime(long time, int minute) {
		return TimeUtil.getStrTime2_1(time + minute * 1000 * 60 + "");
	}

	/**
	 * 获得yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param time
	 * @return
	 */
	public static String getStrTime3(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 获得yyyy.MM.dd HH:mm格式
	 * 
	 * @param time
	 * @return
	 */

	public static String getStrTime4(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 获得yyyyMMdd格式
	 * 
	 * @param time
	 * @return
	 */

	public static String getStrTime5(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("yyyyMMdd");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 获得HH:mm:ss格式
	 * 
	 * @param time
	 * @return
	 */

	public static String getStrTime6(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("HH:mm:ss");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 获得yyyyMMdd格式
	 * 
	 * @param time
	 * @return
	 */

	public static String getStrTime7_1(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("HH:mm");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}
	
	public static String initTime(String time,String type) {
		String mmdd = getStrTimeMMdd(time);
		String weekDay = getStrTimeEEE(time);
		StringBuilder sb = new StringBuilder();
		sb.append(mmdd);
		sb.append("（");
		sb.append(weekDay);
		sb.append("）");
		
		switch (Integer.valueOf(type)) {
		case 1:
			sb.append(" 上午");
			break;
		case 2:
			sb.append(" 下午");
			break;
		case 3:
			sb.append(" 夜间");
			break;
		}
		return sb.toString();
	}

	/**
	 * yyyy-MM-dd-HH
	 * 
	 * @param time
	 * @return
	 */

	public static String getStrTimeHH(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		if (time == null) {
			return null;
		}
		sdf = new SimpleDateFormat("HH");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

	/**
	 * 根据日期获取周几
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return weekday
	 */
	public static String getWeekDay(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();// 获得一个日历
		calendar.set(year, month - 1, day);// 设置当前时间,月份是从0月开始计算
		int number = calendar.get(Calendar.DAY_OF_WEEK) - 1;// 周表示1-7，是从周日开始，
		String[] str = { "周日", "周一", "周二", "周三", "周四", "周五", "周六", };
		return str[number];
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
	 */

	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * @Title: getCurrentTime
	 * @Description: 获取当前的时间 格式yyyy-MM-dd HH:mm
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}

	/**
	 * @Title: getCurrentTime
	 * @Description: 获取当前的时间 格式yyyyMMdd
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getCurrentTime1() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}

	/**
	 * @Title: getCurrentTime
	 * @Description: 获取当前的时间 格式MM-dd
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getCurrentTime2() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("MM-dd");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}

	
	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	// 获取当天0时时间戳
	public static long getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis() / 1000;
	}

	// 获取当天0时时间戳
	public static long getTimesEvening() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis() / 1000;
	}
}
