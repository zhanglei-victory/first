/**
 * 
 */
package com.microsec.ycjc.farm.pojo;

/**
 * @author Administrator
 *
 */
public class Farm {

	/** 养殖场id **/
	private String farmid;
	
	/** 养殖场名**/
	private String farmname;

	/** 养殖场占地面积**/
	private String farmarea;

	/** 养殖场联系人**/
	private String farmlinkman;
	
	/** 养殖场联系方式**/
	private String farmphone;
	
	/** 养殖场地址**/
	private String farmaddress;
	
	/** 温度 **/
	private String temp;
	
	/** 湿度 **/
	private String humidity;
	
	/** 光照强度 **/
	private String light;
	
	/** PH值 **/
	private String phvalue;
	
	/** 灯状态 **/
	private String lightstatus;
	
	/** 风扇状态 **/
	private String fanstatus;
	
	
	/** 养殖场描述 **/
	private String remark;
	
	/** 卫生标准**/
	private String healthness;
	
	/** 经度 **/
	private String lng;
	
	/** 纬度 **/
	private String lat;
	
	/** 经营许可证号码 **/
	private String licenseid;
	
	/** 登记时间 **/
	private String insdatetime;
	
	/** 修改时间 **/
	private String upddatetime;
	
	/** 喂养数量 **/
	private String breednumber;
	private String markID;


	public String getMarkID() {
		return markID;
	}

	public void setMarkID(String markID) {
		this.markID = markID;
	}

	public String getFarmid() {
		return farmid;
	}

	public void setFarmid(String farmid) {
		this.farmid = farmid;
	}

	public String getFarmname() {
		return farmname;
	}

	public void setFarmname(String farmname) {
		this.farmname = farmname;
	}

	public String getFarmarea() {
		return farmarea;
	}

	public void setFarmarea(String farmarea) {
		this.farmarea = farmarea;
	}

	public String getFarmlinkman() {
		return farmlinkman;
	}

	public void setFarmlinkman(String farmlinkman) {
		this.farmlinkman = farmlinkman;
	}

	public String getFarmphone() {
		return farmphone;
	}

	public void setFarmphone(String farmphone) {
		this.farmphone = farmphone;
	}

	public String getFarmaddress() {
		return farmaddress;
	}

	public void setFarmaddress(String farmaddress) {
		this.farmaddress = farmaddress;
	}

	public String getRemark() {
		return remark;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLightstatus() {
		return lightstatus;
	}

	public void setLightstatus(String lightstatus) {
		this.lightstatus = lightstatus;
	}

	public String getFanstatus() {
		return fanstatus;
	}

	public void setFanstatus(String fanstatus) {
		this.fanstatus = fanstatus;
	}

	public String getPhvalue() {
		return phvalue;
	}

	public void setPhvalue(String phvalue) {
		this.phvalue = phvalue;
	}

	public String getHealthness() {
		return healthness;
	}

	public void setHealthness(String healthness) {
		this.healthness = healthness;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLicenseid() {
		return licenseid;
	}

	public void setLicenseid(String licenseid) {
		this.licenseid = licenseid;
	}

	public String getInsdatetime() {
		return insdatetime;
	}

	public void setInsdatetime(String insdatetime) {
		this.insdatetime = insdatetime;
	}

	public String getUpddatetime() {
		return upddatetime;
	}

	public void setUpddatetime(String upddatetime) {
		this.upddatetime = upddatetime;
	}

	public String getBreednumber() {
		return breednumber;
	}

	public void setBreednumber(String breednumber) {
		this.breednumber = breednumber;
	}
}
