package com.xcc.bustraffic.bustraffic.bean;

import java.io.Serializable;

public class BastBean implements Serializable{	private static final long serialVersionUID = 1312643132L;	private boolean success;	private String errorCode;	private String errorMsg;	private Object data;
	public boolean getSuccess() {		return this.success;	}
	public void setSuccess(boolean success) {		this.success = success;	}
	public String getErrorCode() {		return this.errorCode;	}
	public void setErrorCode(String errorCode) {		this.errorCode = errorCode;	}
	public String getErrorMsg() {		return this.errorMsg;	}
	public void setErrorMsg(String errorMsg) {		this.errorMsg = errorMsg;	}
	public Object getData() {		return this.data;	}
	public void setData(Object data) {		this.data = data;	}
	public BastBean() {}
	public BastBean(boolean success, String errorCode, String errorMsg, Object data){
		super();		this.success = success;		this.errorCode = errorCode;		this.errorMsg = errorMsg;		this.data = data;
	}
	public String toString() {
		return "BastBean [success = " + success + ", errorCode = " + errorCode + ", errorMsg = " + errorMsg + ", data = " + data + "]";	}
}
