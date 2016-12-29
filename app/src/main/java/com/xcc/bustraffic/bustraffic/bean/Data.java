package com.xcc.bustraffic.bustraffic.bean;

import java.io.Serializable;

public class Data implements Serializable{	private static final long serialVersionUID = 1057982672L;	private String busType;	private String userPhone;	private String packageDay;	private String wifiLength;	private long imsireTime;	private String trafficNum;	private String imei;	private String imsi;	private String packageName;
	public String getBusType() {		return this.busType;	}
	public void setBusType(String busType) {		this.busType = busType;	}
	public String getUserPhone() {		return this.userPhone;	}
	public void setUserPhone(String userPhone) {		this.userPhone = userPhone;	}
	public String getPackageDay() {		return this.packageDay;	}
	public void setPackageDay(String packageDay) {		this.packageDay = packageDay;	}
	public String getWifiLength() {		return this.wifiLength;	}
	public void setWifiLength(String wifiLength) {		this.wifiLength = wifiLength;	}
	public long getImsireTime() {		return this.imsireTime;	}
	public void setImsireTime(long imsireTime) {		this.imsireTime = imsireTime;	}
	public String getTrafficNum() {		return this.trafficNum;	}
	public void setTrafficNum(String trafficNum) {		this.trafficNum = trafficNum;	}
	public String getImei() {		return this.imei;	}
	public void setImei(String imei) {		this.imei = imei;	}
	public String getImsi() {		return this.imsi;	}
	public void setImsi(String imsi) {		this.imsi = imsi;	}
	public String getPackageName() {		return this.packageName;	}
	public void setPackageName(String packageName) {		this.packageName = packageName;	}
	public Data() {}
	public Data(String busType, String userPhone, String packageDay, String wifiLength, long imsireTime, String trafficNum, String imei, String imsi, String packageName){
		super();		this.busType = busType;		this.userPhone = userPhone;		this.packageDay = packageDay;		this.wifiLength = wifiLength;		this.imsireTime = imsireTime;		this.trafficNum = trafficNum;		this.imei = imei;		this.imsi = imsi;		this.packageName = packageName;
	}
	public String toString() {
		return "Data [busType = " + busType + ", userPhone = " + userPhone + ", packageDay = " + packageDay + ", wifiLength = " + wifiLength + ", imsireTime = " + imsireTime + ", trafficNum = " + trafficNum + ", imei = " + imei + ", imsi = " + imsi + ", packageName = " + packageName + "]";	}
}
