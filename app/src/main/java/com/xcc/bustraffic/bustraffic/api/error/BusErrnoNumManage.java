package com.xcc.bustraffic.bustraffic.api.error;

import com.xcc.bustraffic.bustraffic.BusApplication;
import com.xcc.bustraffic.bustraffic.R;

/**
 * @Description: 错误码映射类
 */
public enum BusErrnoNumManage {

	ERRNO_10001("01", R.string.errno_10001),
	ERRNO_12030("12030", R.string.errno_12030);

    private String errno_num;
    private int errno_msg;  
    
    private BusErrnoNumManage(String errno_num, int errno_msg) {
        this.errno_num = errno_num;  
        this.errno_msg = errno_msg;  
    }  
    
    public static String getErrnoMsg(String errno_num) {  
        for (BusErrnoNumManage e : BusErrnoNumManage.values()) {
            if (e.getErrno_num().equals(errno_num)) {  
                return BusApplication.getContext().getString(e.errno_msg);
            }  
        }  
        return null;  
    }

	public String getErrno_num() {
		return errno_num;
	}

}
