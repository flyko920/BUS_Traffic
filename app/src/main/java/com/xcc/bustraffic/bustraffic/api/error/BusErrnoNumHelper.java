package com.xcc.bustraffic.bustraffic.api.error;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 选择需要返回文字描述的错误码
 *
 */
public class BusErrnoNumHelper {

	public static String showMsg(String errno, String[] errno_nums) {
		List<String> tempList = Arrays.asList(errno_nums);
		if (tempList.contains(errno)) {
			return BusErrnoNumManage.getErrnoMsg(errno);
		} else {
			return null;
		}
	}
}
