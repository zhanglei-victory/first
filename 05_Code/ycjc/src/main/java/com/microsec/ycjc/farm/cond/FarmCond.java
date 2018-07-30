/**
 * 
 */
package com.microsec.ycjc.farm.cond;

/**
 * @author Administrator
 *
 */
public class FarmCond {

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
	
	/** 养殖场描述 **/
	private String remark;

	/** 开始下标 **/
	private int start;
	
	/** 每页条数 **/
	private int singlePagesize;

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

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSinglePagesize() {
		return singlePagesize;
	}

	public void setSinglePagesize(int singlePagesize) {
		this.singlePagesize = singlePagesize;
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
}
